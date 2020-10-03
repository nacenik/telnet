package net.oleksin.socket.command.cdprocessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import net.oleksin.Context;
import net.oleksin.PathsAndFilesWorker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChangeDirRootProcessorTest {
  private ChangeDirRootProcessor processor;
  private Context context;
  private PathsAndFilesWorker worker;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
    worker = mock(PathsAndFilesWorker.class);
    processor = new ChangeDirRootProcessor(worker);
  }
  
  @Test
  void shouldReturnTrue() {
    String path = "/test";
    when(worker.isAbsolute(any())).thenReturn(true);
    
    assertTrue(processor.isExecutable(path));
  }
  
  @Test
  void shouldReturnFalseBecauseEmptyPath() {
    String[] strings = new String[0];
    when(worker.isAbsolute(any())).thenReturn(false);
    
    assertFalse(processor.isExecutable(strings));
  }
  
  @Test
  void shouldReturnFalseBecauseBadPath() {
    String path = "test";
    when(worker.isAbsolute(any())).thenReturn(false);
    
    assertFalse(processor.isExecutable(path.split("\\s")));
    
    verify(worker).isAbsolute(any());
  }
  
  @Test
  void shouldChangeDirectory() {
    String path = "/bin";
    when(context.getPath()).thenReturn(Paths.get("/test/test"));
    when(worker.isDirectory(any())).thenReturn(true);
    processor.changeDirectory(context, path.split("\\s"));
    
    verify(context).setPath(Paths.get(path));
    verify(worker).isDirectory(any());
  }
  
  @Test
  void shouldPrintMessage() {
    String path = "bin";
    when(context.getPath()).thenReturn(Paths.get("/test/test"));
    processor.changeDirectory(context, path.split("\\s"));
    
    verify(context).printLn("Bad name for root!");
  }
}