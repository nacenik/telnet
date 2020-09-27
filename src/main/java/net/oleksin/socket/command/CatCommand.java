package net.oleksin.socket.command;

import net.oleksin.Context;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CatCommand implements Command {
  private String[] args;

  public CatCommand(String[] args) {
    this.args = args;
  }

  @Override
  public void execute(Context context) {
    Path path = Paths.get(String.join("", args));
    try {
      if (path.isAbsolute() && isFile(path)) {
        readFromAbsolutePathFile(context, path);
      } else if (!context.isPathNull()) {
        readFromCurrentPathFile(context, path);
      } else {
        printToOut(context);
      }
    } catch (IOException e) {
      context.printLn("File cannot be read");
    }
  }


  private boolean isFile(Path path) {
    return Files.notExists(path) || !Files.isDirectory(path);
  }

  private void readFromAbsolutePathFile(Context context, Path path)
          throws IOException {
    context.printLn(String.join("\n", Files.readAllLines(path)));
  }

  private void readFromCurrentPathFile(Context context, Path path)
          throws IOException {
    Path newPath = context
            .getPath()
            .resolve(path)
            .toAbsolutePath()
            .normalize();
    if (isFile(newPath)) {
      readFromAbsolutePathFile(context, newPath);
    } else {
      printToOut(context);
    }
  }

  private void printToOut(Context context) {
    context.printLn("Bad name for file");
  }

}
