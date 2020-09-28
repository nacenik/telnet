package net.oleksin.socket.command.cdprocessor;

import net.oleksin.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class ChangeDirCurrentProcessorTest {
  private ChangeDirCurrentProcessor processor;
  private Context context;
  private PrintWriter printWriter;
  private StringWriter stringWriter;
  
  @BeforeEach
  void setUp() {
    processor = new ChangeDirCurrentProcessor();
    stringWriter = new StringWriter();
    printWriter = new PrintWriter(stringWriter);
    context = new Context(printWriter);
  }
  
  @Test
  void shouldReturnFalseBecauseRootPath() {
    String path = "/Users";
    
    assertFalse(processor.isExecutable(path));
  }
  
  @Test
  void shouldReturnFalseBecauseBadPath() {
    String path = "Users / mac";
    
    assertFalse(processor.isExecutable(path.split("\\s")));
  }
  
  @Test
  void shouldReturnFalseBecauseEmptyPath() {
    String[] strings = new String[0];
    
    assertFalse(processor.isExecutable(strings));
  }
  
  @Test
  void shouldReturnTrue() {
    String path = "Users";
    
    assertTrue(processor.isExecutable(path));
  }
  
  @Test
  void shouldSetNewPath() {
    context.setPath(Paths.get("/Users"));
    processor.changeDirectory(context, "mac");
    
    assertEquals("/Users/mac", context.getPath().toString());
  }
  
  @Test
  void shouldNotSetPathBecauseDirectoryNotExist() {
    context.setPath(Paths.get("/Users"));
    processor.changeDirectory(context, "sdasdsadmac");
    
    printWriter.flush();
    String message = stringWriter.toString();
    
    assertEquals("/Users", context.getPath().toString());
    assertEquals("Directory not found!\n", message);
  }
  
  @Test
  void shouldNotSetPathBecauseItIsFile() {
    context.setPath(Paths.get("/Users"));
    processor.changeDirectory(context,
            "mac/IdeaProjects/telnetGit/src/main/resources/File.txt");
    
    printWriter.flush();
    String message = stringWriter.toString();
  
    assertEquals("/Users", context.getPath().toString());
    assertEquals("Directory not found!\n", message);
  }
}