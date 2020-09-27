package net.oleksin.socket.command.cdprocessor;

import net.oleksin.Context;

public interface ChangeDirProcessor {
  boolean isExecutable(String... args);
  
  void changeDirectory(Context context, String... args);
}
