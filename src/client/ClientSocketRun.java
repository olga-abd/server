package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientSocketRun implements Runnable {
    Thread t;
    Socket socket;

    public ClientSocketRun(Socket socket){
        t = new Thread();
        t.start();
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            InputStream is = socket.getInputStream();

            DataInputStream reader = new DataInputStream(is);

            System.out.println(reader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
