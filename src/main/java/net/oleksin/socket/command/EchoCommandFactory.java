package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

public class EchoCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String... args) {
    return new EchoCommand(args);
  }
}
