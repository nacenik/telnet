package net.oleksin;

public interface CommandProvider {
  CommandFactory getCommandFactory(String str);
}
