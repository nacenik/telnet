package net.oleksin.socket.command;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class LsCommandTest {
  
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
    Command command = new LsCommand();
    command.execute(context);
  
    printWriter.flush();
    String expected = "Directory does not exist! Please, input directory!\n";
    String message = stringWriter.toString();
    
    assertEquals(expected, message);
  }
  
  @Test
  void shouldPrintFileTree() {
    Command command = new LsCommand();
    context.setPath(Paths.get("/Users"));
    command.execute(context);
    
    printWriter.flush();
    String expected = ".localized               :\tMon Aug 26 03:26:29 EEST 2019\n"
            + "Shared                   :\tMon Aug 26 03:26:29 EEST 2019\n"
            + "mac                      :\tMon Sep 28 10:04:26 EEST 2020\n"
            + "Guest                    :\tMon Feb 26 11:15:26 EET 2018\n";
    String message = stringWriter.toString();
    
    assertEquals(expected, message);
  }
}