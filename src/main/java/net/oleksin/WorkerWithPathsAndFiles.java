package net.oleksin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkerWithPathsAndFiles {
  
  public boolean isAbsolute(Path path) {
    return path.isAbsolute();
  }
  
  public boolean isFile(Path path) {
    return Files.exists(path) && !Files.isDirectory(path);
  }
  
  public boolean isDirectory(Path path) {
    return Files.isDirectory(path);
  }
  
  public boolean isParent(String path) {
    String parentPath = "..";
    return parentPath.equals(path);
  }
  
  public List<String> readAllLines(Path path) throws IOException {
    return Files.readAllLines(path);
  }
}
