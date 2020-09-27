package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

public class CdCommandFactory implements CommandFactory {
  
  @Override
  public Command getCommand(String... args) {
    return new CdCommand(args);
  }
}
