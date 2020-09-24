package net.oleksin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.oleksin.app.TelnetApplication;

public class Main {
  private ExecutorService executeIt;
  private final int SERVER_PORT = 9819;
  private final int NUMBER_OF_POOLS = 2;

  public Main() {
    executeIt = Executors.newFixedThreadPool(NUMBER_OF_POOLS);
  }

  public static void main(final String[] args) {
    Main main = new Main();
    try (ServerSocket serverSocket = new ServerSocket(main.SERVER_PORT)) {
      System.out.println("Server is working!");
      while (!serverSocket.isClosed()) {
        Socket client = serverSocket.accept();
        main.executeIt.execute(new TelnetApplication(client));
      }
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
}
