package net.oleksin.socket.command;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class EchoCommandTest {
  
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
  void shouldPrintEcho() {
    String echo = "Hello world!    !";
    Command command = new EchoCommand(echo.split("\\s"));
    command.execute(context);
    printWriter.flush();
    
    assertEquals(echo.concat("\n"), stringWriter.toString());
  }
}