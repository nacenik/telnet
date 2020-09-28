package net.oleksin.socket.command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.oleksin.Context;
import net.oleksin.socket.command.cdprocessor.ChangeDirCurrentProcessor;
import net.oleksin.socket.command.cdprocessor.ChangeDirParentProcessor;
import net.oleksin.socket.command.cdprocessor.ChangeDirProcessor;
import net.oleksin.socket.command.cdprocessor.ChangeDirRootProcessor;

class CdCommand implements Command {

  private String[] args;
  private List<ChangeDirProcessor> processors;

  CdCommand(String[] args) {
    processors = Arrays.asList(
            new ChangeDirCurrentProcessor(),
            new ChangeDirParentProcessor(),
            new ChangeDirRootProcessor());
    this.args = args;
  }

  @Override
  public void execute(Context context) {
    Optional<ChangeDirProcessor> processor = processors
            .stream()
            .filter(proc -> proc.isExecutable(args))
            .findFirst();
    if (processor.isPresent()) {
      processor.get().changeDirectory(context, args);
    } else {
      context.printLn(
              "Bad name for directory!");
    }
  }
}
