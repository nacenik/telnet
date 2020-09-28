package net.oleksin.socket.command.cdprocessor;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ChangeDirParentProcessorTest {
  private ChangeDirParentProcessor processor;
  private Context context;
  private PrintWriter printWriter;
  private StringWriter stringWriter;
  
  @BeforeEach
  void setUp() {
    processor = new ChangeDirParentProcessor();
    stringWriter = new StringWriter();
    printWriter = new PrintWriter(stringWriter);
    context = new Context(printWriter);
  }
  
  @Test
  void shouldReturnFalseBecauseBadPath() {
    assertFalse(processor.isExecutable("mac"));
  }
  
  @Test
  void shouldReturnFalseBecauseEmptyString() {
    String [] strings = new String[0];
    
    assertTrue(processor.isExecutable(strings));
  }
  
  @Test
  void shouldReturnTrue() {
    assertTrue(processor.isExecutable(".."));
  }
  
  @Test
  void shouldChangePath() {
    context.setPath(Paths.get("/Users"));
    processor.changeDirectory(context, "..");
    
    assertEquals("/", context.getPath().toString());
  }
  
  @Test
  void shouldNotChangePath() {
    context.setPath(Paths.get("/"));
    processor.changeDirectory(context, "..");
    
    assertEquals("/", context.getPath().toString());
  }
  
  @Test
  void shouldPrintMessage() {
    processor.changeDirectory(context, "..");
  
    printWriter.flush();
    String message = stringWriter.toString();
    
    assertEquals("Directory not exist!\n", message);
  }
}