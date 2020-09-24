package net.oleksin.socket.commandfactory.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CatCommand implements Command {
  private String[] args;

  public CatCommand(String[] args) {
    this.args = args;
  }

  @Override
  public void execute(CommandContext commandContext) {
    Path path = Paths.get(String.join("", args));
    try {
      if (path.isAbsolute() && isFile(path)) {
        readFromAbsolutePathFile(commandContext, path);
      } else if (!commandContext.isPathNull()) {
        readFromCurrentPathFile(commandContext, path);
      } else {
        printToOut(commandContext);
      }
    } catch (IOException e) {
      commandContext.addToOutput("File cannot be read");
    }
  }


  private boolean isFile(Path path) {
    return Files.notExists(path) || !Files.isDirectory(path);
  }

  private void readFromAbsolutePathFile(CommandContext commandContext, Path path)
          throws IOException {
    commandContext.addToOutput(String.join("\n", Files.readAllLines(path)));
  }

  private void readFromCurrentPathFile(CommandContext commandContext, Path path)
          throws IOException {
    Path newPath = commandContext
            .getPath()
            .resolve(path)
            .toAbsolutePath()
            .normalize();
    if (isFile(newPath)) {
      readFromAbsolutePathFile(commandContext, newPath);
    } else {
      printToOut(commandContext);
    }
  }

  private void printToOut(CommandContext commandContext) {
    commandContext.addToOutput("Bad name for file");
  }

}
