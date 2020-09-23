package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.CatCommand;
import net.oleksin.socket.commandfactory.command.Command;

public class CatCommandFactory implements CommandFactory {
  
  @Override
  public Command getCommand(String... args) {
    return new CatCommand(args);
  }
}
