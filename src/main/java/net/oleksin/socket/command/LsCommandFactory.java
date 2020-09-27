package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

public class LsCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String ... args) {
    if (args.length <= 0) {
      return new LsCommand();
    }
    return new ErrorCommand();
  }
}
