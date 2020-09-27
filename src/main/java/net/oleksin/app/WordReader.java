package net.oleksin.app;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;
import net.oleksin.CommandProvider;
import net.oleksin.socket.command.Command;

class WordReader {
  Command returnCommand(Scanner in, CommandProvider provider, Logger logger) {
    String entry = in.nextLine();
    String message = String.format("READ from message %s - %s", Thread.currentThread().getName(), entry);
    logger.info(message);
    String[] args = entry.split("\\s");
    return provider.getCommandFactory(args[0])
            .getCommand(
                    Arrays.copyOfRange(
                            args, 1, args.length));
  }
}
