package net.oleksin.socket.command;

import net.oleksin.CommandFactory;
import net.oleksin.WorkerWithPathsAndFiles;

class CatCommandFactory implements CommandFactory {
  
  @Override
  public Command getCommand(String... args) {
    return new CatCommand(args, new WorkerWithPathsAndFiles());
  }
}
