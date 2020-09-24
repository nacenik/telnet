package net.oleksin.socket.commandfactory.command.cdprocessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.socket.commandfactory.command.CommandContext;

public class CurrentProcessor implements Processor {

  @Override
  public boolean isExecutable(String... args) {
    final String parentDirectory = "..";
    return !Paths.get(String.join("", args)).isAbsolute()
            && !args[0].equals(parentDirectory);
  }

  @Override
  public void changeDirectory(CommandContext commandContext, String... args) {
    if (!commandContext.isPathNull()) {
      Path path = commandContext
              .getPath()
              .resolve(Paths.get(String.join("", args)))
              .toAbsolutePath()
              .normalize();
      if (isDirectory(path)) {
        commandContext.setPath(path);
        commandContext.addToOutput(commandContext
                        .getPath()
                        .toString());
      } else {
        printMessage(commandContext);
      }
    } else {
      printMessage(commandContext);
    }
  }

  private boolean isDirectory(Path path) {
    return Files.isDirectory(path);
  }

  private void printMessage(CommandContext commandContext) {
    commandContext.addToOutput("Directory not found!");
  }
}
