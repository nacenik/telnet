package net.oleksin.socket.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.oleksin.Context;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;

class LsCommandTest {
  
  private Context context;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
  }
  
  @Test
  void shouldPrintMessage() {
    String expected = "Directory does not exist! Please, input directory!";
    LsCommand command = new LsCommand();
    when(context.isPathNull()).thenReturn(true);
    
    command.execute(context);
    
    verify(context).isPathNull();
    verify(context).printLn(expected);
  }
  
  @Test
  void shouldPrintFileTree() {
    LsCommand command = new LsCommand();
    when(context.isPathNull()).thenReturn(false);
    when(context.getPath()).thenReturn(Paths.get("/"));
  
    command.execute(context);
  
    verify(context).isPathNull();
    verify(context, atLeastOnce()).printLn(anyString());
  }
}