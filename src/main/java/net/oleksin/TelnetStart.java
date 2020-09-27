package net.oleksin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import net.oleksin.app.TelnetApplication;
import net.oleksin.socket.command.CommandProviderImpl;

public class TelnetStart {
  private final ExecutorService executeIt;
  private static final int SERVER_PORT = 9819;
  private static final int NUMBER_OF_POOLS = 2;
  private final CommandProviderImpl provider;
  private Logger logger;
  
  public TelnetStart() {
    executeIt = Executors.newFixedThreadPool(NUMBER_OF_POOLS);
    provider = new CommandProviderImpl();
    logger = Logger.getLogger(this.getClass().getName());
  }
  
  public void startServer() {
    try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
      logger.info("Server is working!");
      while (!serverSocket.isClosed()) {
        Socket client = serverSocket.accept();
        executeIt.execute(new TelnetApplication(client, provider));
      }
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
}
