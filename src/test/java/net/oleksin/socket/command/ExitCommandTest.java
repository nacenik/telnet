package net.oleksin.socket.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.oleksin.Context;

import static org.mockito.Mockito.*;

class ExitCommandTest {
  
  private Context context;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
  }
  
  @Test
  void shouldChangeConnectedFlag() {
    String expected = "Goodbye Man! See you soon!\n";
    ExitCommand command = new ExitCommand();
    
    command.execute(context);
    
    verify(context).changeConnected();
    verify(context).printLn(expected);
  }
}