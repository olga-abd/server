package server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.List;

public class SocketRun implements Runnable {
    protected Socket socket;
    protected Thread t;
    protected List<Socket> socketList;
    protected String fileName;

    public SocketRun(Socket socket, List<Socket> socketList) {
        this.socket = socket;
        this.socketList = socketList;
        t = new Thread(this, " ");
        t.start();
    }

    @Override
    public void run() {
        try {
            getFile();
            sendAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = new String();
        String line = in.readLine();
        while (line != null) {
            str += line + "\n\r";
            line = in.readLine();
        }


        System.out.println(str);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        fileName = ".\\final_file" + ts.getTime() + ".txt";
        Path path = Paths.get(fileName);
        //if(Files.notExists(path)) Files.createFile(path);

        Files.write(path,str.getBytes(), StandardOpenOption.CREATE);
        System.out.println("Записано");
        //in.close();

        //socket.close();
    }

    public void  sendAnswer() throws IOException {
        String msg = "Файл " + fileName + " записан";


        for (Socket s : socketList) {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(msg);
            out.newLine();
            out.flush();
        }
        System.out.println(msg);


    }


}
