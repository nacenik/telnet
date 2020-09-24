package net.oleksin.socket.commandfactory.command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.oleksin.socket.commandfactory.command.cdprocessor.CurrentProcessor;
import net.oleksin.socket.commandfactory.command.cdprocessor.ParentProcessor;
import net.oleksin.socket.commandfactory.command.cdprocessor.Processor;
import net.oleksin.socket.commandfactory.command.cdprocessor.RootProcessor;

public class CdCommand implements Command {

  private String[] args;
  private List<Processor> processors;

  public CdCommand(String[] args) {
    processors = Arrays.asList(
            new CurrentProcessor(),
            new ParentProcessor(),
            new RootProcessor());
    this.args = args;
  }

  @Override
  public void execute(CommandContext commandContext) {
    Optional<Processor> processor = processors
            .stream()
            .filter(proc -> proc.isExecutable(args))
            .findFirst();
    if (processor.isPresent()) {
      processor.get().changeDirectory(commandContext, args);
    } else {
      commandContext.addToOutput(
              "Bad name for directory!");
    }
  }
}
