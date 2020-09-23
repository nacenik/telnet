package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.Command;
import net.oleksin.socket.commandfactory.command.EchoCommand;

public class EchoCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String... args) {
    return new EchoCommand(args);
  }
}
