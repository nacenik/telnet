package net.oleksin;

import net.oleksin.socket.command.Command;

public interface CommandFactory {
  Command getCommand(String ... args);
}
