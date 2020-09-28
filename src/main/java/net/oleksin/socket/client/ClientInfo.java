package net.oleksin.socket.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClientInfo that = (ClientInfo) o;
    return Objects.equals(clientIp, that.clientIp) &&
            Objects.equals(connectTime, that.connectTime) &&
            Objects.equals(disconnectTime, that.disconnectTime);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(clientIp, connectTime, disconnectTime);
  }
}
