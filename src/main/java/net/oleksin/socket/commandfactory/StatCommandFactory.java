package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.Command;
import net.oleksin.socket.commandfactory.command.ErrorCommand;
import net.oleksin.socket.commandfactory.command.StatCommand;

public class StatCommandFactory implements CommandFactory {
  
  @Override
  public Command getCommand(String... args) {
    if (args.length <= 0) {
      return new StatCommand();
    }
    return new ErrorCommand();
  }
}
