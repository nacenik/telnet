package net.oleksin.socket.commandfactory.command;

public class StatCommand implements Command {

  @Override
  public void execute(CommandContext commandContext) {
    commandContext.addToOutput(
            commandContext
            .getClientInfo()
            .toString());
  }
}
