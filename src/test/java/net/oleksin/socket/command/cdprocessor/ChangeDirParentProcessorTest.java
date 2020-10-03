package net.oleksin.socket.command.cdprocessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.Context;
import net.oleksin.PathsAndFilesWorker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChangeDirParentProcessorTest {
  private ChangeDirParentProcessor processor;
  private Context context;
  private PathsAndFilesWorker worker;
  
  @BeforeEach
  void setUp() {
    worker = mock(PathsAndFilesWorker.class);
    processor = new ChangeDirParentProcessor(worker);
    context = mock(Context.class);
  }
  
  @Test
  void shouldReturnFalseBecauseBadPath() {
    String path = "test";
    
    assertFalse(processor.isExecutable(path));
  }
  
  @Test
  void shouldReturnFalseBecauseEmptyString() {
    String [] strings = new String[0];
    
    assertTrue(processor.isExecutable(strings));
  }
  
  @Test
  void shouldReturnTrue() {
    String path = "..";
    
    assertTrue(processor.isExecutable(path));
  }
  
  @Test
  void shouldChangePath() {
    Path expected = Paths.get("/test");
    when(context.getPath()).thenReturn(Paths.get("/test/test"));
    processor.changeDirectory(context, "..");
    
    verify(context).setPath(expected);
  }
  
  @Test
  void shouldNotChangePath() {
    when(context.getPath()).thenReturn(Paths.get("/"));
    processor.changeDirectory(context, "..");
  
    verify(context, times(0)).setPath(any());
  }
  
  @Test
  void shouldPrintMessage() {
    String path = "test";
    when(context.isPathNull()).thenReturn(true);
    processor.changeDirectory(context, path.split("\\s"));
    
    verify(context).isPathNull();
    verify(context).printLn("Directory not exist!");
  }
}