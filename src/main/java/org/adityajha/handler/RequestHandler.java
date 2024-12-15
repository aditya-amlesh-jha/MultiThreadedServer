package org.adityajha.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public enum RequestHandler {
    INSTANCE;
    ExecutorService executorService;

    public void initializeExecutor(int threadPoolSize) {
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(threadPoolSize);
        }
    }

    public void serveRequest(Socket clientConnection){
            executorService.execute(()->{
                try {
                    String message = String.format("Socket connection established with client :: %s", clientConnection.getRemoteSocketAddress());
                    System.out.println(message);
                    PrintWriter toClient = new PrintWriter(clientConnection.getOutputStream(), true);
                    toClient.println("Hi Client!");
                    toClient.close();

//                    BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
//                    String clientMessage = fromClient.readLine();
//                    System.out.println(clientMessage);
//                    fromClient.close();

                    clientConnection.close();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            });
    }

    public void shutdownExecutor() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
        }
    }
}
