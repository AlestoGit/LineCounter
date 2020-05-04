package dev.alesto.linecounter;

import static dev.alesto.linecounter.misc.Messages.BYE;
import static dev.alesto.linecounter.misc.Messages.IO_EXCEPTION_OCCURRED;
import static dev.alesto.linecounter.misc.Messages.NO_FILE_SPECIFIED;
import static dev.alesto.linecounter.misc.Messages.UNEXPECTED_EXCEPTION_OCCURRED;
import static dev.alesto.linecounter.misc.Messages.UNKNOWN_COMMAND;

import dev.alesto.linecounter.exception.FileProcessingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Main {

  LineCounter lineCounter;
  ConsolePrinter consolePrinter;

  public Main() {
    this.lineCounter = new LineCounter();
    this.consolePrinter = new ConsolePrinter();
  }

  public static void main(String[] args) {
    new Main().run();
  }

  public void run() {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        String fullCommand = reader.readLine();
        final String[] fullCommandParts = fullCommand.split(" ");
        switch (fullCommandParts[0]) {
          case "count":
            if (fullCommandParts.length < 2) {
              System.out.println(NO_FILE_SPECIFIED);
            } else {
              String fileName = fullCommand.substring(fullCommand.indexOf(" ") + 1).trim();
              handleCountCommand(fileName);
            }
            break;
          case "quit":
            System.out.println(BYE);
            System.exit(0);
          default:
            System.out.println(UNKNOWN_COMMAND);
        }
      }
    } catch (IOException e) {
      System.out.println(IO_EXCEPTION_OCCURRED);
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println(UNEXPECTED_EXCEPTION_OCCURRED);
      e.printStackTrace();
    }
  }

  public void handleCountCommand(String fileName) {
    try {
      LineCountInfo lineCountInfo = lineCounter.getLineCountInfo(Paths.get(fileName));
      consolePrinter.printLineCountInfo(lineCountInfo);
    } catch (FileProcessingException e) {
      System.out.println(e.getMessage() + " ; Path: " + e.getPath());
      // here should be appropriate logging
      e.printStackTrace();
    }
  }
}
