package net.oleksin.socket.commandfactory.command;

public class EchoCommand implements Command {
  private final String[] args;

  public EchoCommand(String... args) {
    this.args = args;
  }

  @Override
  public void execute(CommandContext commandContext) {
    commandContext.addToOutput(String.join(" ", args));
  }
}
