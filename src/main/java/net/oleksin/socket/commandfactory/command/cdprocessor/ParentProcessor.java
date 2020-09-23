package net.oleksin.socket.commandfactory.command.cdprocessor;

import net.oleksin.socket.commandfactory.command.CommandContext;

public class ParentProcessor implements Processor {
  
  @Override
  public boolean isExecutable(String... args) {
    return true;
  }

  @Override
  public void changeDirectory(CommandContext commandContext, String... args) {
  }
}
