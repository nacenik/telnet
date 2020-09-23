package net.oleksin.socket.client;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientInfo {
    private String clientIP;
    private LocalDateTime connectTime;
    private LocalDateTime disconnectTime;

    public ClientInfo(String clientIP, LocalDateTime connectTime) {
        this.clientIP = clientIP;
        this.connectTime = connectTime;
    }

    public void setDisconnectTime(LocalDateTime disconnectTime) {
        this.disconnectTime = disconnectTime;
    }

    @Override
    public String toString() {
        return "clientIP='" + clientIP + '\''
                + ", connectTime="
                + connectTime.format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy, hh:mm"));
    }
}
