package dev.alesto.linecounter;

public class ConsolePrinter {

  public void printLineCountInfo(LineCountInfo info) {
    System.out.println(buildLine(info));
    if (info.getContents() != null) {
      info.getContents().forEach(this::printLineCountInfo);
    }
  }

  private String buildLine(LineCountInfo info) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < info.getDepth(); i++) {
      sb.append("\t");
    }
    sb.append(info.getFileName()).append(" : ").append(info.getCount());
    return sb.toString();
  }
}
