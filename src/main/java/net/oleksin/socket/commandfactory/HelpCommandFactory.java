package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.Command;
import net.oleksin.socket.commandfactory.command.HelpCommand;

public class HelpCommandFactory implements CommandFactory {
  @Override
  public Command getCommand(String... args) {
    return new HelpCommand();
  }
}
