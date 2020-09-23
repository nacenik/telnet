package net.oleksin.socket.commandfactory.command.cdprocessor;

import net.oleksin.socket.commandfactory.command.CommandContext;

public interface Processor {
  boolean isExecutable(String... args);
  
  void changeDirectory(CommandContext commandContext, String... args);
}
