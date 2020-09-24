package net.oleksin.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import net.oleksin.socket.client.ClientInfo;
import net.oleksin.socket.commandfactory.command.Command;
import net.oleksin.socket.commandfactory.command.CommandContext;
import net.oleksin.socket.commandfactory.CommandProvider;

public class TelnetApplication implements Runnable {
  private CommandContext commandContext;
  private CommandProvider provider;
  private PrintWriter out;
  private Scanner in;
  
  private static Socket clientDialog;

  public TelnetApplication(Socket client) {
    TelnetApplication.clientDialog = client;
  }

  @Override
  public void run() {
    try {
      createAll();
      
      commandContext.setClientInfo(new ClientInfo(
              clientDialog.getLocalAddress().toString(),
              LocalDateTime.now()));

      while (commandContext.isConnectionFlag()) {
        talkToUser();
      }

      System.out.println("Client disconnected");
      
      closeAll();
      
      System.out.println("Closing connections and channels - DONE.");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
  
  private void createAll() throws IOException {
    out = new PrintWriter(clientDialog.getOutputStream());
    in = new Scanner(clientDialog.getInputStream());
  
    printGreeting();
    
    provider = new CommandProvider();
    commandContext = new CommandContext(out);
  
  }
  
  private void printGreeting() {
    out.write("Hello Man!\n");
    out.flush();
  }
  
  private void talkToUser() {
    out.write("-> ");
    out.flush();
    
    String entry = in.nextLine();
  
    System.out.println("READ from message "
            + Thread.currentThread().getName()
            + " - "
            + entry);
    
    String[] args = entry.split("\\s");
    Command command = provider.getCommandFactory(args[0])
            .getCommand(
                    Arrays.copyOfRange(
                            args, 1, args.length));
  
    System.out.println("\nServer write to channel "
              + Thread.currentThread().getName());
    command.execute(commandContext);
    out.flush();
  }
  
  private void closeAll() throws IOException {
    in.close();
    out.close();
    clientDialog.close();
  }
}
