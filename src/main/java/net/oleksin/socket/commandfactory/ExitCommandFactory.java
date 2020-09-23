package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.Command;
import net.oleksin.socket.commandfactory.command.ExitCommand;

public class ExitCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String... args) {
    return new ExitCommand();
  }
}
