package net.oleksin;

import java.io.PrintWriter;
import java.nio.file.Path;
import net.oleksin.socket.client.ClientInfo;

public class Context {
  private boolean connected;
  private Path path;
  private ClientInfo clientInfo;
  private PrintWriter out;

  public Context(PrintWriter out) {
    connected = true;
    this.out = out;
  }

  public ClientInfo getClientInfo() {
    return clientInfo;
  }

  public void setClientInfo(ClientInfo clientInfo) {
    this.clientInfo = clientInfo;
  }

  public boolean isConnected() {
    return connected;
  }

  public Path getPath() {
    return path;
  }

  public void setPath(Path path) {
    this.path = path;
  }

  public boolean isPathNull() {
    return path == null;
  }

  public void changeConnected() {
    connected = false;
  }

  public void printLn(String string) {
    out.printf("%s%n", string);
  }
}
