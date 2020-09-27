package net.oleksin.socket.command.cdprocessor;

import net.oleksin.Context;

public class ChangeDirParentProcessor implements ChangeDirProcessor {
  
  @Override
  public boolean isExecutable(String... args) {
    final String parentFlag = "..";
    return args.length == 0
            || (args.length == 1
            && args[0].equals(parentFlag));
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
