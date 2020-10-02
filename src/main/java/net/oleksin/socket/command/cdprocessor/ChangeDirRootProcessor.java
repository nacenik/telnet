package net.oleksin.socket.command.cdprocessor;

import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.Context;
import net.oleksin.WorkerWithPathsAndFiles;

public class ChangeDirRootProcessor implements ChangeDirProcessor {
  
  private WorkerWithPathsAndFiles worker;
  
  public ChangeDirRootProcessor(WorkerWithPathsAndFiles worker) {
    this.worker = worker;
  }
  
  @Override
  public boolean isExecutable(String... args) {
    return args.length == 1
            && worker.isAbsolute(Paths.get(args[0]));
  }

  @Override
  public void changeDirectory(Context context, String... args) {
    Path path = Paths.get(args[0]);
    if (path.isAbsolute() && worker.isDirectory(path)) {
      context.setPath(path);
      context.printLn(context.getPath().toString());
    } else {
      printException(context);
    }
  }
  
  private void printException(Context context) {
    context.printLn("Bad name for root!");
  }
}
