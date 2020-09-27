package net.oleksin.socket.command;

import net.oleksin.Context;

public class ExitCommand implements Command {

  @Override
  public void execute(Context context) {
    context.changeConnected();
    context.printLn("Goodbye Man! See you soon!");
  }
}
