package net.oleksin.socket.command;

import net.oleksin.Context;

class ErrorCommand implements Command {

  @Override
  public void execute(Context context) {
    context.printLn("Error! You had a mistake in command!");
  }
}