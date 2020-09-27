package net.oleksin.socket.command;

import net.oleksin.Context;

public class EchoCommand implements Command {
  private final String[] args;

  public EchoCommand(String... args) {
    this.args = args;
  }

  @Override
  public void execute(Context context) {
    context.printLn(String.join(" ", args));
  }
}
