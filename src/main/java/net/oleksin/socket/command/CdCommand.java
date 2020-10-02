package net.oleksin.socket.command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.oleksin.Context;
import net.oleksin.WorkerWithPathsAndFiles;
import net.oleksin.socket.command.cdprocessor.ChangeDirCurrentProcessor;
import net.oleksin.socket.command.cdprocessor.ChangeDirParentProcessor;
import net.oleksin.socket.command.cdprocessor.ChangeDirProcessor;
import net.oleksin.socket.command.cdprocessor.ChangeDirRootProcessor;

class CdCommand implements Command {

  private String[] args;
  private WorkerWithPathsAndFiles worker;

  CdCommand(String[] args, WorkerWithPathsAndFiles worker) {
    this.args = args;
    this.worker = worker;
  }

  @Override
  public void execute(Context context) {
    List<ChangeDirProcessor> processors = Arrays.asList(
            new ChangeDirCurrentProcessor(worker),
            new ChangeDirParentProcessor(worker),
            new ChangeDirRootProcessor(worker));
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
