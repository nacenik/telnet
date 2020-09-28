package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

class CdCommandFactory implements CommandFactory {
  
  @Override
  public Command getCommand(String... args) {
    return new CdCommand(args);
  }
}
