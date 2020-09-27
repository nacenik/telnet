package net.oleksin.socket.command.cdprocessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.Context;

public class ChangeDirRootProcessor implements ChangeDirProcessor {
  
  @Override
  public boolean isExecutable(String... args) {
    return args.length == 1
            && Paths.get(args[0]).isAbsolute();
  }

  @Override
  public void changeDirectory(Context context, String... args) {
    Path path = Paths.get(args[0]);
    if (path.isAbsolute() && isDirectory(path)) {
      context.setPath(path);
      context.printLn(context.getPath().toString());
    } else {
      printException(context);
    }
  }
  
  private void printException(Context context) {
    context.printLn("Bad name for root!");
  }

  private boolean isDirectory(Path path) {
    return Files.isDirectory(path);
  }
}
