package net.oleksin.socket.command.cdprocessor;

import net.oleksin.Context;
import net.oleksin.PathsAndFilesWorker;

public class ChangeDirParentProcessor implements ChangeDirProcessor {
  
  private PathsAndFilesWorker worker;
  
  public ChangeDirParentProcessor(PathsAndFilesWorker worker) {
    this.worker = worker;
  }
  
  @Override
  public boolean isExecutable(String... args) {
    return args.length == 0
            || (args.length == 1
            && worker.isParent(args[0]));
  }

  @Override
  public void changeDirectory(Context context, String... args) {
    if (!context.isPathNull()) {
      setParentRoot(context);
    } else {
      context.printLn("Directory not exist!");
    }
  }
  
  private void setParentRoot(Context context) {
    if (context.getPath()
            .toAbsolutePath()
            .getNameCount() > 0) {
      context.setPath(context.getPath()
                      .toAbsolutePath()
                      .getParent());
    }
    context.printLn(context.getPath().toString());
  }

}
