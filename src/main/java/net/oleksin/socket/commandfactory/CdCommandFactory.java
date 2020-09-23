package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.CdCommand;
import net.oleksin.socket.commandfactory.command.Command;

public class CdCommandFactory implements CommandFactory {
  
  @Override
  public Command getCommand(String... args) {
    return new CdCommand(args);
  }
}
