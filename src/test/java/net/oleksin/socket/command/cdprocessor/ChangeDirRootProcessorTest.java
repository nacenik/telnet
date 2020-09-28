package net.oleksin.socket.command.cdprocessor;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ChangeDirRootProcessorTest {
  private ChangeDirRootProcessor processor;
  private Context context;
  private PrintWriter printWriter;
  private StringWriter stringWriter;
  
  @BeforeEach
  void setUp() {
    processor = new ChangeDirRootProcessor();
    stringWriter = new StringWriter();
    printWriter = new PrintWriter(stringWriter);
    context = new Context(printWriter);
  }
  
  @Test
  void shouldReturnTrue() {
    assertTrue(processor.isExecutable("/Users"));
  }
  
  @Test
  void shouldReturnFalseBecauseEmptyPath() {
    String[] strings = new String[0];
    
    assertFalse(processor.isExecutable(strings));
  }
  
  @Test
  void shouldReturnFalseBecauseBadPath() {
    assertFalse(processor.isExecutable("Users"));
  }
  
  @Test
  void shouldChangeDirectory() {
    context.setPath(Paths.get("/Users/mac"));
    processor.changeDirectory(context, "/bin");
    
    assertEquals("/bin", context.getPath().toString());
  }
  
  @Test
  void shouldNotChangeDirectory() {
    context.setPath(Paths.get("/Users/mac"));
    processor.changeDirectory(context, "bin");
    printWriter.flush();
    String message = stringWriter.toString();
  
    assertEquals("Bad name for root!\n", message);
    assertEquals("/Users/mac", context.getPath().toString());
  }
}