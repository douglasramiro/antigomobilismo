package br.com.drsource;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class GpsSocketThread extends Thread{
    protected Socket socket;

    public GpsSocketThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            Logger.getLogger(e.getClass().getName()).severe(e.getMessage());
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    out.writeBytes(line + "\n\r");
                    out.flush();
                    Logger.getLogger(line.getClass().getName()).info("Linha: "+line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
