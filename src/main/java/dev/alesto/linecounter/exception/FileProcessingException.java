package dev.alesto.linecounter.exception;

public class FileProcessingException extends RuntimeException {

  private final String path;

  public FileProcessingException(String path, String message) {
    super(message);
    this.path = path;
  }

  public FileProcessingException(String path, String message, Throwable cause) {
    super(message, cause);
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
