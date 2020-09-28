package net.oleksin.socket.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.Context;

class CatCommand implements Command {
  private final String[] args;

  CatCommand(String[] args) {
    this.args = args;
  }

  @Override
  public void execute(Context context) {
    if (args.length == 1) {
      Path path = Paths.get(args[0]);
      try {
        if (path.isAbsolute() && isFile(path)) {
          readFromAbsolutePathFile(context, path);
        } else if (!context.isPathNull()) {
          readFromCurrentPathFile(context, path);
        } else {
          printMessage(context, "Bad name for file");
        }
      } catch (IOException e) {
        printMessage(context, "File cannot be read");
      }
    } else {
      printMessage(context, "Bad name for path");
    }
  }
  
  private void printMessage(Context context, String message) {
    context.printLn(message);
  }


  private boolean isFile(Path path) {
    return Files.exists(path) && !Files.isDirectory(path);
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
      printMessage(context, "File not found in current path");
    }
  }
}
