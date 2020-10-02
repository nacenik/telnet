package net.oleksin.socket.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.oleksin.Context;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EchoCommandTest {
  
  private Context context;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
  }
  
  @Test
  void shouldPrintEcho() {
    String echo = "Hello world!    !";
    EchoCommand command = new EchoCommand(echo.split("\\s"));
    command.execute(context);
    
    verify(context).printLn(echo);
  }
}