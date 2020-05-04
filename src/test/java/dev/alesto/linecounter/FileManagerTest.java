package dev.alesto.linecounter;

import static dev.alesto.linecounter.TestConstants.RESOURCES_PATH;
import static org.junit.Assert.assertEquals;

import dev.alesto.linecounter.exception.FileProcessingException;
import dev.alesto.linecounter.misc.Messages;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileManagerTest {

  @Rule public ExpectedException expectedException = ExpectedException.none();
  FileManager fileManager = new FileManager();

  @Test
  public void readFileTest() {
    final Stream<String> fileLines =
        fileManager.getFileLines(Paths.get(RESOURCES_PATH + "2lines_file.txt"));
    assertEquals("File should contain two lines", 2, fileLines.count());
  }

  @Test
  public void readEmptyFileTest() {
    final Stream<String> fileLines =
        fileManager.getFileLines(Paths.get(RESOURCES_PATH + "empty_file.txt"));
    assertEquals("File should contain no lines", 0, fileLines.count());
  }

  @Test
  public void readEmptyLinesFileTest() {
    final Stream<String> fileLines =
        fileManager.getFileLines(Paths.get(RESOURCES_PATH + "2emptylines.txt"));
    assertEquals("File should be read properly and contain 2 lines", 2, fileLines.count());
  }

  @Test
  public void readNoExtensionFileTest() {
    final Stream<String> fileLines =
        fileManager.getFileLines(Paths.get(RESOURCES_PATH + "noextension"));
    assertEquals("File should be read properly and contain 4 lines", 4, fileLines.count());
  }

  @Test
  public void readNonExistingFileTest() {
    expectedException.expect(FileProcessingException.class);
    expectedException.expectMessage(Messages.FILE_NOT_EXISTS);

    fileManager.getFileLines(Paths.get(RESOURCES_PATH + "notexist"));
  }

  @Test
  public void listContentsTest() {
    FileManager fm = new FileManager();
    final Stream<Path> pathStream = fm.listDirectoryContents(Paths.get(RESOURCES_PATH));
    assertEquals(
        "Test resources path list should contain root and 6 more paths", 7, pathStream.count());
  }
}
