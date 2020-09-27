package net.oleksin.socket.command;

import net.oleksin.CommandFactory;

public class CatCommandFactory implements CommandFactory {
  
  @Override
  public Command getCommand(String... args) {
    return new CatCommand(args);
  }
}