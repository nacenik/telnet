package net.oleksin.socket.command;

import net.oleksin.CommandFactory;
import net.oleksin.PathsAndFilesWorker;

class CdCommandFactory implements CommandFactory {
  
  @Override
  public Command getCommand(String... args) {
    return new CdCommand(args, new PathsAndFilesWorker());
  }
}
