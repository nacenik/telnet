package net.oleksin.socket.command;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.Context;
import net.oleksin.WorkerWithPathsAndFiles;

class CatCommand implements Command {
  private final String[] args;
  private final WorkerWithPathsAndFiles worker;

  CatCommand(String[] args, WorkerWithPathsAndFiles worker) {
    this.args = args;
    this.worker = worker;
  }

  @Override
  public void execute(Context context) {
    if (args.length == 1) {
      Path path = Paths.get(args[0]);
      try {
        if (worker.isAbsolute(path)
                && worker.isFile(path)) {
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
  

  private void readFromAbsolutePathFile(Context context, Path path)
          throws IOException {
    context.printLn(String.join("\n", worker.readAllLines(path)));
  }

  private void readFromCurrentPathFile(Context context, Path path)
          throws IOException {
    Path newPath = context
            .getPath()
            .resolve(path)
            .toAbsolutePath()
            .normalize();
    if (worker.isFile(newPath)) {
      readFromAbsolutePathFile(context, newPath);
    } else {
      printMessage(context, "File not found in current path");
    }
  }
}
