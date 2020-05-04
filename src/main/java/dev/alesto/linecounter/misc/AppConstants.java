package dev.alesto.linecounter.misc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AppConstants {
  public static Charset CHARSET = StandardCharsets.ISO_8859_1;
  public static List<String> FILE_EXCLUSIONS = new ArrayList<>();

  private AppConstants() {
    FILE_EXCLUSIONS.add(".DS_Store");
  }
}
