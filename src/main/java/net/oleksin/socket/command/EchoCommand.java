package net.oleksin.socket.command;

import net.oleksin.Context;

class EchoCommand implements Command {
  private final String[] args;

  EchoCommand(String... args) {
    this.args = args;
  }

  @Override
  public void execute(Context context) {
    context.printLn(String.join(" ", args));
  }
}
