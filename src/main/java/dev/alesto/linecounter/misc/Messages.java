package dev.alesto.linecounter.misc;

public class Messages {

  public static final String UNKNOWN_COMMAND = "Unknown command";
  public static final String NO_FILE_SPECIFIED =
      "No filename or directory specified. Use \"count [filename]\"";
  public static final String BYE = "Bye";
  public static final String IO_EXCEPTION_OCCURRED =
      "An IO Exception occurred. Shutting down the program";
  public static final String UNEXPECTED_EXCEPTION_OCCURRED =
      "Unexpected Exception occurred. Shutting down the program";
  public static final String FAILED_TO_READ_FILE = "Failed to read the file";
  public static final String FAILED_TO_GET_DIRECTORY_CONTENTS = "Failed to get directory contents";
  public static final String FILE_NOT_EXISTS = "File or directory not exists";

  private Messages() {};
}
