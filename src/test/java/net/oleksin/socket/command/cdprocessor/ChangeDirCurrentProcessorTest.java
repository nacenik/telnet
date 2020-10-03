package net.oleksin.socket.command.cdprocessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.oleksin.Context;
import net.oleksin.PathsAndFilesWorker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChangeDirCurrentProcessorTest {
  private ChangeDirCurrentProcessor processor;
  private Context context;
  private PathsAndFilesWorker worker;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
    worker = mock(PathsAndFilesWorker.class);
    processor = new ChangeDirCurrentProcessor(worker);
  }
  
  @Test
  void shouldReturnFalseBecauseRootPath() {
    String path = "/test";
    when(worker.isAbsolute(any())).thenReturn(true);
    
    assertFalse(processor.isExecutable(path.split("\\s")));
    
    verify(worker).isAbsolute(any());
  }
  
  @Test
  void shouldReturnFalseBecauseBadPath() {
    String path = "test / test";
    
    assertFalse(processor.isExecutable(path.split("\\s")));
  }
  
  @Test
  void shouldReturnFalseBecauseEmptyPath() {
    String[] strings = new String[0];
    when(worker.isAbsolute(any())).thenReturn(false);
    
    assertFalse(processor.isExecutable(strings));
  }
  
  @Test
  void shouldReturnTrue() {
    String path = "test/test";
    when(worker.isAbsolute(any())).thenReturn(false);
    when(worker.isParent(anyString())).thenReturn(true);
    
    assertTrue(processor.isExecutable(path));
  
    verify(worker).isAbsolute(any());
    verify(worker).isParent(anyString());
  }
  
  @Test
  void shouldSetNewPath() {
    Path expected = Paths.get("/test");
    String path = "test";
    when(context.getPath()).thenReturn(Paths.get("/"));
    when(context.isPathNull()).thenReturn(false);
    when(worker.isDirectory(any())).thenReturn(true);
    
    processor.changeDirectory(context, path.split("\\s"));
    
    verify(context).setPath(expected);
    verify(context).isPathNull();
    verify(worker).isDirectory(any());
  }
  
  @Test
  void shouldNotSetPath() {
    String path = "test";
    when(context.getPath()).thenReturn(Paths.get("/"));
    when(context.isPathNull()).thenReturn(false);
    when(worker.isDirectory(any())).thenReturn(false);
    
    processor.changeDirectory(context, path);
    
    verify(context, times(0)).setPath(any());
    verify(context).printLn("Directory not found!");
  }
  
  @Test
  void shouldSetPath() {
    String path = "test/test";
    when(context.getPath()).thenReturn(Paths.get("/"));
    when(context.isPathNull()).thenReturn(false);
    when(worker.isDirectory(any())).thenReturn(true);
  
    processor.changeDirectory(context, path);
  
    verify(context).setPath(any());
    verify(context).isPathNull();
    verify(worker).isDirectory(any());
  }
}