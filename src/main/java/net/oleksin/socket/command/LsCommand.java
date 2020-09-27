package net.oleksin.socket.command;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

import net.oleksin.Context;

public class LsCommand implements Command {
  @Override
  public void execute(final Context context) {
    if (context.isPathNull()) {
      printException(context);
    } else {
      walkFileTree(context);
    }
  }
  
  private void walkFileTree(Context context) {
    try {
      Files.walkFileTree(context.getPath(),
              Set.of(), 1, new MyFileVisitor(context));
    } catch (IOException e) {
      printException(context);
    }
  }
  
  private void printException(Context context) {
    context.printLn(
            "Directory does not exist! Please, input directory!");
  }

  private class MyFileVisitor extends SimpleFileVisitor<Path> {
    private final Context context;

    public MyFileVisitor(Context context) {
      this.context = context;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
      context.printLn(
              String.format("%-25.25s:\t%tc", file.getFileName(),
                      attrs.lastModifiedTime().toMillis()));
      return FileVisitResult.CONTINUE;
    }
  }

}

