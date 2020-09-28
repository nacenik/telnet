package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

class EchoCommandFactory implements CommandFactory {

  @Override
  public Command getCommand(String... args) {
    return new EchoCommand(args);
  }
}
