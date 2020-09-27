package net.oleksin.socket.command;

import net.oleksin.CommandFactory;
import net.oleksin.CommandProvider;

import java.util.HashMap;
import java.util.Map;

public class CommandProviderImpl implements CommandProvider {
  private final Map<String, CommandFactory> factoryMap;
  
  public CommandProviderImpl() {
    factoryMap = new HashMap<>();
    factoryMap.put("ls", new LsCommandFactory());
    factoryMap.put("cat", new CatCommandFactory());
    factoryMap.put("cd", new CdCommandFactory());
    factoryMap.put("echo", new EchoCommandFactory());
    factoryMap.put("stat", new StatCommandFactory());
    factoryMap.put("exit", new ExitCommandFactory());
    factoryMap.put("help", new HelpCommandFactory());
  }
  
  public CommandFactory getCommandFactory(String str) {
    return factoryMap.containsKey(str)
            ? factoryMap.get(str) : new ErrorCommandFactory();
  }
}
