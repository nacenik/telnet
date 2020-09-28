package net.oleksin.socket.command;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {
  
  private Context context;
  private PrintWriter printWriter;
  private StringWriter stringWriter;
  
  @BeforeEach
  void setUp() {
    stringWriter = new StringWriter();
    printWriter = new PrintWriter(stringWriter);
    context = new Context(printWriter);
  }
  
  @Test
  void shouldChangeConnectedFlag() {
    String expected = "Goodbye Man! See you soon!\n";
    Command command = new ExitCommand();
    
    assertTrue(context.isConnected());
    
    command.execute(context);
    printWriter.flush();
  
    assertFalse(context.isConnected());
    assertEquals(expected, stringWriter.toString());
  }
}