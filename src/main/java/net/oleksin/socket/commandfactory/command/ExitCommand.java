package net.oleksin.socket.commandfactory.command;

public class ExitCommand implements Command {

  @Override
  public void execute(CommandContext commandContext) {
    commandContext.changeConnectionFlag();
    commandContext.addToOutput("Goodbye Man! See you soon!");
  }
}
