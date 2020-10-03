package net.oleksin.socket.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.oleksin.Context;
import net.oleksin.PathsAndFilesWorker;

import static org.mockito.Mockito.*;

class CdCommandTest {
  
  private Context context;
  private PathsAndFilesWorker worker;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
    worker = mock(PathsAndFilesWorker.class);
  }
  
  @Test
  void shouldPrintMessage() {
    CdCommand command = new CdCommand("/test /test".split("\\s"), worker);
    command.execute(context);
    
    verify(context).printLn("Bad name for directory!");
  }
  
}