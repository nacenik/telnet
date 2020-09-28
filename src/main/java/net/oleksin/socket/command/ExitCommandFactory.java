package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

class ExitCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String... args) {
    return new ExitCommand();
  }
}
