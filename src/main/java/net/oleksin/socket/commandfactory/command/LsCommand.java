package net.oleksin.socket.commandfactory.command;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;

public class LsCommand implements Command {
  @Override
  public void execute(final CommandContext commandContext) {
    if (commandContext.isPathNull()) {
      printToOut(commandContext);
    } else {
      readFromFile(commandContext);
    }
  }
  
  private void readFromFile(CommandContext commandContext) {
    try {
      Files.walkFileTree(commandContext.getPath(),
              Collections.emptySet(), 1, new MyFileVisitor(commandContext));
    } catch (IOException e) {
      printToOut(commandContext);
    }
  }
  
  private void printToOut(CommandContext commandContext) {
    commandContext.addToOutput(
            "Directory does not exist! Please, input directory!");
  }

  private class MyFileVisitor extends SimpleFileVisitor<Path> {
    private final CommandContext commandContext;

    public MyFileVisitor(CommandContext commandContext) {
      this.commandContext = commandContext;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
      commandContext.addToOutput(
              String.format("%-25.25s:\t%tc", file.getFileName(),
                      attrs.lastModifiedTime().toMillis()));
      return FileVisitResult.CONTINUE;
    }
  }

}

