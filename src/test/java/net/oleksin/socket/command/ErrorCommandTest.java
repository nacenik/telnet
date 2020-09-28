package net.oleksin.socket.command;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class ErrorCommandTest {
  
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
  void shouldPrintMessage() {
    String expected = "Error! You had a mistake in command!\n";
    Command command = new ErrorCommand();
    command.execute(context);
    printWriter.flush();
    
    assertEquals(expected, stringWriter.toString());
  }
}