package dev.alesto.linecounter;

import dev.alesto.linecounter.misc.AppConstants;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LineCounter {

  private FileManager fileManager;

  public LineCounter() {
    this.fileManager = new FileManager();
  }

  public LineCountInfo getLineCountInfo(Path path) {
    return getLineCountInfo(path, 0);
  }

  private LineCountInfo getLineCountInfo(Path path, int depth) {
    LineCountInfo lineCountInfo = new LineCountInfo();
    lineCountInfo.setFileName(path.getFileName().toString());
    lineCountInfo.setDepth(depth);

    if (Files.isDirectory(path)) {
      AtomicInteger counter = new AtomicInteger();
      int newDepth = depth + 1;

      List<LineCountInfo> contents =
          fileManager
              .listDirectoryContents(path)
              .filter(contentPath -> !contentPath.equals(path) && isPathCountable(contentPath))
              .map(contentPath -> getLineCountInfo(contentPath, newDepth))
              .peek(contentLineCountInfo -> counter.addAndGet(contentLineCountInfo.getCount()))
              .collect(Collectors.toList());

      lineCountInfo.setCount(counter.get());
      lineCountInfo.setContents(contents);
    } else {
      lineCountInfo.setCount((int) fileManager.getFileLines(path).count());
    }

    return lineCountInfo;
  }

  private boolean isPathCountable(Path contentPath) {
    // here should be more appropriate filter for particular OS.
    return AppConstants.FILE_EXCLUSIONS.stream()
        .noneMatch(exclusion -> contentPath.toString().contains(exclusion));
  }
}
