package net.oleksin.socket.command.cdprocessor;

import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.Context;
import net.oleksin.PathsAndFilesWorker;

public class ChangeDirCurrentProcessor implements ChangeDirProcessor {
  
  private PathsAndFilesWorker worker;
  
  public ChangeDirCurrentProcessor(PathsAndFilesWorker worker) {
    this.worker = worker;
  }
  
  @Override
  public boolean isExecutable(String... args) {
    return args.length == 1 && !worker.isAbsolute(Paths.get(args[0]))
            && !worker.isParent(args[0]);
  }

  @Override
  public void changeDirectory(Context context, String... args) {
    if (!context.isPathNull()) {
      Path path = context
              .getPath()
              .resolve(Paths.get(args[0]))
              .toAbsolutePath()
              .normalize();
      if (worker.isDirectory(path)) {
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
  
  private void printMessage(Context context) {
    context.printLn("Directory not found!");
  }
}
