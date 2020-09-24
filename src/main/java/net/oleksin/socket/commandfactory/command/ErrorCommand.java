package net.oleksin.socket.commandfactory.command;

public class ErrorCommand implements Command {

  @Override
  public void execute(CommandContext commandContext) {
    commandContext.addToOutput("Error! You had a mistake in command!");
  }
}