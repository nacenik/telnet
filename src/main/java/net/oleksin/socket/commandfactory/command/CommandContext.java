package net.oleksin.socket.commandfactory.command;

import java.io.PrintWriter;
import java.nio.file.Path;
import net.oleksin.socket.client.ClientInfo;

public class CommandContext {
  private boolean connectionFlag;
  private Path path;
  private ClientInfo clientInfo;
  private PrintWriter out;

  public CommandContext(PrintWriter out) {
    connectionFlag = true;
    this.out = out;
  }

  public ClientInfo getClientInfo() {
    return clientInfo;
  }

  public void setClientInfo(ClientInfo clientInfo) {
    this.clientInfo = clientInfo;
  }

  public boolean isConnectionFlag() {
    return connectionFlag;
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

  public void changeConnectionFlag() {
    connectionFlag = !connectionFlag;
  }

  public void addToOutput(String string) {
    out.printf("%s%n", string);
  }
}
