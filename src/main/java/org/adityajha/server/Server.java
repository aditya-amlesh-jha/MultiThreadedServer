package org.adityajha.server;
import org.adityajha.handler.RequestHandler;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private RequestHandler requestHandler;
    private int serverPort;
    private int serverTimeout;

    public Server() {
    }

    public Server(RequestHandler requestHandler, int serverPort, int serverTimeout){
        this.requestHandler = requestHandler;
        this.serverPort = serverPort;
        this.serverTimeout = serverTimeout;
    }

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }

    public void setRequestHandler(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getServerTimeout() {
        return serverTimeout;
    }

    public void setServerTimeout(int serverTimeout) {
        this.serverTimeout = serverTimeout;
    }

    public void listenAndServeRequest(){
        try(ServerSocket serverSocket = new ServerSocket(serverPort)){

            serverSocket.setSoTimeout(serverTimeout);
            InetAddress inetAddress = InetAddress.getLocalHost();

            String serverMessage = String.format("Server is listening on %s :: %d", inetAddress, serverPort);
            System.out.println(serverMessage);

            while (true){
                Socket acceptedConnection = serverSocket.accept();
                requestHandler.serveRequest(acceptedConnection);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            requestHandler.shutdownExecutor();
        }
    }
}
