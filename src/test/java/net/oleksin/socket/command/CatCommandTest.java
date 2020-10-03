package net.oleksin.socket.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import net.oleksin.Context;
import net.oleksin.PathsAndFilesWorker;

import static org.mockito.Mockito.*;

class CatCommandTest {
  
  private Context context;
  private PathsAndFilesWorker worker;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
    worker = mock(PathsAndFilesWorker.class);
  }
  
  @Test
  void shouldPrintMessageBecauseBadName() {
    CatCommand command = new CatCommand("/test /test".split("\\s"), worker);
    command.execute(context);
    
    verify(context).printLn("Bad name for path");
  }
  
  @Test
  void shouldPrintMessageBecauseIsDirectory() {
    String stringPath = "/test/test";
    Path expectedPath = Paths.get(stringPath);
    CatCommand command = new CatCommand(stringPath.split("\\s"), worker);
    
    when(worker.isAbsolute(any())).thenReturn(true);
    when(worker.isFile(any())).thenReturn(false);
    when(context.isPathNull()).thenReturn(true);
    command.execute(context);
  
    verify(worker).isAbsolute(expectedPath);
    verify(worker).isFile(expectedPath);
    verify(context).isPathNull();
    verify(context).printLn("Bad name for file");
  }
  
  @Test
  void shouldCannotReadFile() throws IOException {
    String stringPath = "/test/test.txt";
    Path expectedPath = Paths.get(stringPath);
    CatCommand command = new CatCommand(stringPath.split("\\s"), worker);
    
    when(worker.isAbsolute(any())).thenReturn(true);
    when(worker.isFile(any())).thenReturn(true);
    when(worker.readAllLines(any())).thenThrow(IOException.class);
    command.execute(context);
  
    verify(worker).isAbsolute(expectedPath);
    verify(worker).isFile(expectedPath);
    verify(worker).readAllLines(expectedPath);
    verify(context).printLn("File cannot be read");
  }
  
  @Test
  void shouldReadFileFromCurrentPath() throws IOException {
    String stringPath = "test/test.txt";
    Path filePath = Paths.get(stringPath);
    Path expectedPath = Paths.get("/" + stringPath);
    CatCommand command = new CatCommand(stringPath.split("\\s"), worker);
    
    when(worker.isAbsolute(any())).thenReturn(false);
    when(worker.isFile(any())).thenReturn(true);
    when(context.isPathNull()).thenReturn(false);
    when(context.getPath()).thenReturn(Paths.get("/"));
    when(worker.readAllLines(any())).thenReturn(Collections.singletonList("Hello"));
    command.execute(context);
    
    verify(worker).isAbsolute(filePath);
    verify(worker).isFile(expectedPath);
    verify(worker).readAllLines(expectedPath);
    verify(context).printLn("Hello");
  }
  
  @Test
  void shouldReadFileFromAbsolutePath() throws IOException {
    String stringPath = "/test/test.txt";
    Path filePath = Paths.get(stringPath);
    CatCommand command = new CatCommand(stringPath.split("\\s"), worker);
    
    when(worker.isAbsolute(any())).thenReturn(true);
    when(worker.isFile(any())).thenReturn(true);
    when(worker.readAllLines(any())).thenReturn(Collections.singletonList("Hello"));
    command.execute(context);
    
    verify(worker).isAbsolute(filePath);
    verify(worker).isFile(filePath);
    verify(worker).readAllLines(filePath);
    verify(context).printLn("Hello");
  }
  
}