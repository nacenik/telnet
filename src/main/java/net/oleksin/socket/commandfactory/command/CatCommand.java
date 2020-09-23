package net.oleksin.socket.commandfactory.command;

public class CatCommand implements Command {
  private String[] args;
  
  public CatCommand(String[] args) {
    this.args = args;
  }
  
  @Override
  public void execute(CommandContext commandContext) {
  }
}