package net.oleksin.socket.command;

import net.oleksin.Context;

public interface Command {
  void execute(Context context);
}
