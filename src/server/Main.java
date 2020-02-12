package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args){
        try {
            serverCycle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void serverCycle() throws IOException {
        ServerSocket serverSocket = new ServerSocket(10000);
        List<Socket> sockets = new ArrayList<>();

        System.out.println("Wait..");
        while(true){
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket.toString());
            sockets.add(clientSocket);
            SocketRun socketRun = new SocketRun(clientSocket, sockets);

            socketRun.run();

        }
    }


}
