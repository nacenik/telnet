package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.Command;
import net.oleksin.socket.commandfactory.command.ErrorCommand;

public class ErrorCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String... args) {
    return new ErrorCommand();
  }
}
