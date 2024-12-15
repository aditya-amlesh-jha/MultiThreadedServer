package org.adityajha;

import org.adityajha.config.ApplicationConfig;
import org.adityajha.handler.RequestHandler;
import org.adityajha.server.Server;

public class Application {
    public static void main(String[] args) {
        ApplicationConfig configHandler = ApplicationConfig.INSTANCE;
        RequestHandler requestHandler = RequestHandler.INSTANCE;

        Server server = new Server();

        int serverPort = Integer.parseInt(configHandler.getProperty("server.port"));
        int threadPoolSize = Integer.parseInt(configHandler.getProperty("thread.pool.size"));
        int serverTimeout = Integer.parseInt(configHandler.getProperty("server.timeout.seconds"));

        requestHandler.initializeExecutor(threadPoolSize);
        server.setServerPort(serverPort);
        server.setServerTimeout(serverTimeout);
        server.setRequestHandler(requestHandler);

        Thread t1 = new Thread(server::listenAndServeRequest);

        try{
            t1.start();
            t1.join();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
