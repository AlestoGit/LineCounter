package dev.alesto.linecounter;

import java.util.List;

public class LineCountInfo {
  private String fileName;
  private int count;
  private int depth;
  private List<LineCountInfo> contents;

  public LineCountInfo() {}

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public List<LineCountInfo> getContents() {
    return contents;
  }

  public void setContents(List<LineCountInfo> contents) {
    this.contents = contents;
  }
}
