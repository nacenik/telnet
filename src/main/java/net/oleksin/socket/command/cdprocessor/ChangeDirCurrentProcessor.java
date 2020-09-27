package net.oleksin.socket.command.cdprocessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.Context;

public class ChangeDirCurrentProcessor implements ChangeDirProcessor {

  @Override
  public boolean isExecutable(String... args) {
    final String parentDirectory = "..";
    return args.length == 1 && !Paths.get(args[0]).isAbsolute()
            && !args[0].equals(parentDirectory);
  }

  @Override
  public void changeDirectory(Context context, String... args) {
    if (!context.isPathNull()) {
      Path path = context
              .getPath()
              .resolve(Paths.get(args[0]))
              .toAbsolutePath()
              .normalize();
      if (isDirectory(path)) {
        context.setPath(path);
        context.printLn(context
                        .getPath()
                        .toString());
      } else {
        printMessage(context);
      }
    } else {
      printMessage(context);
    }
  }

  private boolean isDirectory(Path path) {
    return Files.isDirectory(path);
  }

  private void printMessage(Context context) {
    context.printLn("Directory not found!");
  }
}
