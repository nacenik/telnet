package net.oleksin.socket.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.oleksin.Context;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ErrorCommandTest {
  
  private Context context;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
  }
  
  @Test
  void shouldPrintMessage() {
    String expected = "Error! You had a mistake in command!";
    ErrorCommand command = new ErrorCommand();
    command.execute(context);
    
    verify(context).printLn(expected);
  }
}