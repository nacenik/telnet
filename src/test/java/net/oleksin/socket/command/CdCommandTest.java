package net.oleksin.socket.command;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CdCommandTest {
  
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
    Command command = new CdCommand("/Users/mac/IdeaProjects/ telnetGit/src/main/resources/File.txt".split("\\s"));
    command.execute(context);
  
    printWriter.flush();
    String message = stringWriter.toString();
  
    assertEquals("Bad name for directory!\n", message);
  }
  
  @Test
  void shouldChangeDirectory() {
    String directory = "/Users";
    Command command = new CdCommand(directory.split("\\s"));
    
    assertNull(context.getPath());
    
    command.execute(context);
    
    assertNotNull(context.getPath());
    assertEquals(directory, context.getPath().toString());
  }
  
  @Test
  void shouldChangeCurrentDirectory() {
    String directory = "/Users";
    context.setPath(Paths.get(directory));
    Command command = new CdCommand("mac".split("\\s"));
    
    assertNotNull(context.getPath());
    
    command.execute(context);
    
    assertEquals(directory.concat("/mac"), context.getPath().toString());
  }
  
  @Test
  void shouldChangeRootDirectory() {
    context.setPath(Paths.get("/Users/mac"));
    String directory = "/bin";
    Command command = new CdCommand(directory.split("\\s"));
    
    assertNotNull(context.getPath());
    
    command.execute(context);
    
    assertEquals(directory, context.getPath().toString());
  }
  
  @Test
  void shouldChangeParentDirectory() {
    context.setPath(Paths.get("/Users/mac"));
    Command command = new CdCommand("..".split("\\s"));
    
    assertNotNull(context.getPath());
    
    command.execute(context);
    
    assertEquals("/Users", context.getPath().toString());
  }
}