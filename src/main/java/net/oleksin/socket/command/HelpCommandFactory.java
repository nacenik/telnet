package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

class HelpCommandFactory implements CommandFactory {
  @Override
  public Command getCommand(String... args) {
    return new HelpCommand();
  }
}
