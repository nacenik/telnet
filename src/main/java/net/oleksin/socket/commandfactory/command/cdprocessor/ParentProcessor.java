package net.oleksin.socket.commandfactory.command.cdprocessor;

import net.oleksin.socket.commandfactory.command.CommandContext;

public class ParentProcessor implements Processor {
  
  @Override
  public boolean isExecutable(String... args) {
    final String parentFlag = "..";
    return args.length == 0
            || (args.length == 1
            && args[0].equals(parentFlag));
  }

  @Override
  public void changeDirectory(CommandContext commandContext, String... args) {
    if (!commandContext.isPathNull()) {
      setParentRoot(commandContext);
    } else {
      commandContext.addToOutput("Directory not exist!");
    }
  }
  
  private void setParentRoot(CommandContext commandContext) {
    if (commandContext.getPath()
            .toAbsolutePath()
            .getNameCount() > 0) {
      commandContext.setPath(commandContext.getPath()
                      .toAbsolutePath()
                      .getParent());
    }
    commandContext.addToOutput(commandContext.getPath().toString());
  }

}
