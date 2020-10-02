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
import static org.mockito.Mockito.*;

class StatCommandTest {
  
  private Context context;
  
  @BeforeEach
  void setUp() {
    context = mock(Context.class);
  }
  
  @Test
  void shouldPrintClientInfo() {
    LocalDateTime localDateTime = LocalDateTime.now();
    String ip = "192.0.0.1";
    ClientInfo expected = new ClientInfo(ip, localDateTime);
    StatCommand command = new StatCommand();
    
    when(context.getClientInfo()).thenReturn(expected);
    command.execute(context);
    
    verify(context).printLn(expected.toString());
  }
}