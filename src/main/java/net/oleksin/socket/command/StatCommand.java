package net.oleksin.socket.command;

import net.oleksin.Context;

class StatCommand implements Command {

  @Override
  public void execute(Context context) {
    context.printLn(
            context
            .getClientInfo()
            .toString());
  }
}
