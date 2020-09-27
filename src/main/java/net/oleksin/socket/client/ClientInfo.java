package net.oleksin.socket.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientInfo {
  private final String clientIp;
  private final LocalDateTime connectTime;
  private LocalDateTime disconnectTime;

  public ClientInfo(String clientIp, LocalDateTime connectTime) {
    this.clientIp = clientIp;
    this.connectTime = connectTime;
  }
  
  public void setDisconnectTime(LocalDateTime disconnectTime) {
    this.disconnectTime = disconnectTime;
  }
  
  @Override
  public String toString() {
    return "clientIP='" + clientIp + '\''
            + ", connectTime="
            + connectTime.format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy, hh:mm"));
  }
}
