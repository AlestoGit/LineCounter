package dev.alesto.linecounter;

import static dev.alesto.linecounter.misc.AppConstants.CHARSET;
import static dev.alesto.linecounter.misc.Messages.FAILED_TO_GET_DIRECTORY_CONTENTS;
import static dev.alesto.linecounter.misc.Messages.FAILED_TO_READ_FILE;
import static dev.alesto.linecounter.misc.Messages.FILE_NOT_EXISTS;
import static java.nio.file.Files.notExists;

import dev.alesto.linecounter.exception.FileProcessingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileManager {

  public Stream<Path> listDirectoryContents(Path directoryPath) {
    try {
      return Files.walk(directoryPath, 1);
    } catch (IOException e) {
      throw new FileProcessingException(
          directoryPath.toString(), FAILED_TO_GET_DIRECTORY_CONTENTS, e);
    }
  }

  public Stream<String> getFileLines(Path path) {
    if (notExists(path)) {
      throw new FileProcessingException(path.toString(), FILE_NOT_EXISTS);
    }
    try {
      return Files.lines(path, CHARSET);
    } catch (IOException e) {
      throw new FileProcessingException(path.toString(), FAILED_TO_READ_FILE, e);
    }
  }
}
