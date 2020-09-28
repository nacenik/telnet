package net.oleksin.socket.command;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CatCommandTest {
  
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
  void shouldPrintMessageBecauseBadName() {
    Command command = new CatCommand("/Users/mac/IdeaProjects/ telnetGit/src/main/resources/File.txt".split("\\s"));
    command.execute(context);
    
    printWriter.flush();
    String message = stringWriter.toString();
    
    assertEquals("Bad name for path\n", message);
  }
  
  @Test
  void shouldPrintMessageBecauseIsDirectory() {
    Command command = new CatCommand("/Users/mac/IdeaProjects/telnetGit/src/main/resources/".split("\\s"));
    command.execute(context);
    
    printWriter.flush();
    String message = stringWriter.toString();
    
    assertEquals("Bad name for file\n", message);
  }
  
  @Test
  void shouldCannotReadFile() {
    Command command = new CatCommand("IdeaProjects/telnetGit/src/main/resources/File.txt".split("\\s"));
    context.setPath(Paths.get("/Users"));
    command.execute(context);
    
    printWriter.flush();
    String message = stringWriter.toString();
    
    assertEquals("File not found in current path\n", message);
  }
  
}