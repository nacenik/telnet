package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.Command;
import net.oleksin.socket.commandfactory.command.ErrorCommand;
import net.oleksin.socket.commandfactory.command.LsCommand;

public class LsCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String ... args) {
    if (args.length <= 0) {
      return new LsCommand();
    }
    return new ErrorCommand();
  }
}
