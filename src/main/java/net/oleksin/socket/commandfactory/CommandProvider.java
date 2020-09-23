package net.oleksin.socket.commandfactory;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
  private final Map<String, CommandFactory> factoryMap;
  
  public CommandProvider() {
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
