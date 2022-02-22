package br.com.drsource;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GpsSocket extends Thread{

    private final Integer port;

    public GpsSocket(Integer port) {
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            new GpsSocketThread(socket).start();
        }
    }
}
