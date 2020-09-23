package net.oleksin.socket.commandfactory.command.cdprocessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.socket.commandfactory.command.CommandContext;

public class CurrentProcessor implements Processor {

  @Override
  public boolean isExecutable(String... args) {
    return true;
  }

  @Override
  public void changeDirectory(CommandContext commandContext, String... args) {
  }
}
