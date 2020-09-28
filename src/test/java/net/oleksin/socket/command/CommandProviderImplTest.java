package net.oleksin.socket.command;

import net.oleksin.CommandFactory;
import net.oleksin.CommandProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandProviderImplTest {
  
  CommandProvider provider;
  
  @BeforeEach
  void setUp() {
    provider = new CommandProviderImpl();
  }
  
  @Test
  void shouldReturnLsCommandFactory() {
    CommandFactory command = provider.getCommandFactory("ls");
    assertTrue(command instanceof LsCommandFactory);
    assertEquals("net.oleksin.socket.command.LsCommandFactory",command.getClass().getName());
  }
  
  @Test
  void shouldReturnCatCommandFactory() {
    CommandFactory command = provider.getCommandFactory("cat");
    assertTrue(command instanceof CatCommandFactory);
    assertEquals("net.oleksin.socket.command.CatCommandFactory",command.getClass().getName());
  }
  
  @Test
  void shouldReturnCdCommandFactory() {
    CommandFactory command = provider.getCommandFactory("cd");
    assertTrue(command instanceof CdCommandFactory);
    assertEquals("net.oleksin.socket.command.CdCommandFactory",command.getClass().getName());
  }
  
  @Test
  void shouldReturnEchoCommandFactory() {
    CommandFactory command = provider.getCommandFactory("echo");
    assertTrue(command instanceof EchoCommandFactory);
    assertEquals("net.oleksin.socket.command.EchoCommandFactory",command.getClass().getName());
  }
  
  @Test
  void shouldReturnStatCommandFactory() {
    CommandFactory command = provider.getCommandFactory("stat");
    assertTrue(command instanceof StatCommandFactory);
    assertEquals("net.oleksin.socket.command.StatCommandFactory",command.getClass().getName());
  }
  
  @Test
  void shouldReturnExitCommandFactory() {
    CommandFactory command = provider.getCommandFactory("exit");
    assertTrue(command instanceof ExitCommandFactory);
    assertEquals("net.oleksin.socket.command.ExitCommandFactory",command.getClass().getName());
  }
  
  @Test
  void shouldReturnErrorCommandFactory() {
    CommandFactory command = provider.getCommandFactory("EcHo");
    assertTrue(command instanceof ErrorCommandFactory);
    assertEquals("net.oleksin.socket.command.ErrorCommandFactory",command.getClass().getName());
  }
}