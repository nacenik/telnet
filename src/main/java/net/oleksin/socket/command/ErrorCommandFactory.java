package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

class ErrorCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String... args) {
    return new ErrorCommand();
  }
}
