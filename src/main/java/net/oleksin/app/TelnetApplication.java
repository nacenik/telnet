package net.oleksin.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;
import net.oleksin.Context;
import net.oleksin.socket.client.ClientInfo;
import net.oleksin.socket.command.Command;
import net.oleksin.socket.command.CommandProviderImpl;

public class TelnetApplication implements Runnable {
  private Context context;
  private final CommandProviderImpl provider;
  private Logger logger;
  private PrintWriter out;
  private Scanner in;
  private WordReader wordReader;
  
  private final Socket clientDialog;

  public TelnetApplication(Socket client, CommandProviderImpl provider) {
    this.logger = Logger.getLogger(this.getClass().getName());
    this.clientDialog = client;
    this.provider = provider;
    this.wordReader = new WordReader();
  }

  @Override
  public void run() {
    try {
      createAll();
      
      context.setClientInfo(new ClientInfo(
              clientDialog.getLocalAddress().toString(),
              LocalDateTime.now()));

      while (context.isConnected()) {
        talkToUser();
      }
      
      logger.info("Client disconnected");
      
      closeAll();
      
      logger.info("Closing connections and channels - DONE.");
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
  }
  
  private void createAll() throws IOException {
    out = new PrintWriter(clientDialog.getOutputStream());
    in = new Scanner(clientDialog.getInputStream());
  
    printGreeting();
    
    context = new Context(out);
  }
  
  private void printGreeting() {
    out.write("Hello Man!\n");
    out.flush();
  }
  
  private void talkToUser() {
    out.write("-> ");
    out.flush();
    
    Command command = wordReader.returnCommand(in, provider, logger);
    logger.info("Server write to channel "
              + Thread.currentThread().getName());
    command.execute(context);
    out.flush();
  }
  
  private void closeAll() throws IOException {
    in.close();
    out.close();
    clientDialog.close();
  }
}
