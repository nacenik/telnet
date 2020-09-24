package net.oleksin.socket.commandfactory.command.cdprocessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.socket.commandfactory.command.CommandContext;

public class RootProcessor implements Processor {
  
  @Override
  public boolean isExecutable(String... args) {
    return Paths.get(String.join("", args)).isAbsolute();
  }

  @Override
  public void changeDirectory(CommandContext commandContext, String... args) {
    Path path = Paths.get(String.join("", args));
    if (path.isAbsolute() && isDirectory(path)) {
      commandContext.setPath(path);
      commandContext.addToOutput(commandContext.getPath().toString());
    } else {
      commandContext.addToOutput("Bad name for root!");
    }
  }

  private boolean isDirectory(Path path) {
    return Files.isDirectory(path);
  }
}
