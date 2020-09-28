package net.oleksin.socket.command;

import net.oleksin.Context;
import net.oleksin.socket.client.ClientInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StatCommandTest {
  
  private Context context;
  private LocalDateTime localDateTime;
  
  @BeforeEach
  void setUp() {
    localDateTime = LocalDateTime.now();
    PrintWriter printWriter = new PrintWriter(OutputStream.nullOutputStream());
    context = new Context(printWriter);
  }
  
  @Test
  void shouldPrintClientInfo() {
    String ip = "192.0.0.1";
    ClientInfo expected = new ClientInfo(ip, localDateTime);
    ClientInfo actual = new ClientInfo(ip, localDateTime);
    context.setClientInfo(actual);
    
    assertEquals(expected, actual);
  }
}