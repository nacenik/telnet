package net.oleksin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.oleksin.app.TelnetApplication;

public class Main {
  private ExecutorService executeIt;
  private final int serverPort = 9819;

  public Main() {
    executeIt = Executors.newFixedThreadPool(2);
  }

  public static void main(final String[] args) {
    Main main = new Main();
    try (ServerSocket serverSocket = new ServerSocket(main.serverPort)) {
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
