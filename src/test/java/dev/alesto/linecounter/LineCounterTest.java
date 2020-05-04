package dev.alesto.linecounter;

import static dev.alesto.linecounter.TestConstants.RESOURCES_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Paths;
import org.junit.Ignore;
import org.junit.Test;

public class LineCounterTest {

  LineCounter lineCounter = new LineCounter();

  @Test
  public void countLinesTest() {
    String fileName = "2lines_file.txt";
    LineCountInfo lineCountInfo =
        lineCounter.getLineCountInfo(Paths.get(RESOURCES_PATH + fileName));

    assertNotNull(lineCountInfo);
    assertEquals("Info should have expected filename", fileName, lineCountInfo.getFileName());
    assertEquals("Info should contain two lines", 2, lineCountInfo.getCount());
  }

  @Test
  public void countLinesDirectoryTest() {
    String directoryName = "folder/innerfolder";
    LineCountInfo lineCountInfo =
        lineCounter.getLineCountInfo(Paths.get(RESOURCES_PATH + directoryName));

    assertNotNull("Info should not be null", lineCountInfo);
    assertEquals("First info should have depth 0", 0, lineCountInfo.getDepth());
    assertNotNull("Info should contain two more info", lineCountInfo.getContents());
    assertEquals("Info should contain two more info", 2, lineCountInfo.getContents().size());

    lineCountInfo
        .getContents()
        .forEach(
            innerInfo ->
                assertEquals("depth for inner info should be equal 1", 1, innerInfo.getDepth()));

    assertEquals("directory lines count should be equal 8", 8, lineCountInfo.getCount());

    int totalInnerLines =
        lineCountInfo.getContents().stream()
            .map(LineCountInfo::getCount)
            .reduce(0, Integer::sum);

    assertEquals(
        "The inner files lines count should be equal to directory lines count",
        lineCountInfo.getCount(),
        totalInnerLines);
  }

  @Test
  @Ignore
  public void countLinesEmptyDirectoryTest() {
    String directoryName = "emptyfolder";
    LineCountInfo lineCountInfo =
        lineCounter.getLineCountInfo(Paths.get(RESOURCES_PATH + directoryName));

    assertNotNull("Info should not be null", lineCountInfo);
    assertEquals(
        "Info should have expected directory name", directoryName, lineCountInfo.getFileName());
    assertEquals("Info should contain no lines", 0, lineCountInfo.getCount());
  }
}
