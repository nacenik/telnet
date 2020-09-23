package net.oleksin.socket.commandfactory;

import net.oleksin.socket.commandfactory.command.Command;

public interface CommandFactory {
  Command getCommand(String ... args);
}
