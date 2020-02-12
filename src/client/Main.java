package client;

import java.io.*;
import java.net.Socket;

public class Main {
    public static void main (String[] args){


        try (Socket socket = new Socket("localhost",10000)){

            //ClientSocketRun csr = new ClientSocketRun(socket);
            BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()));


            System.out.println("Введите send для отправки: ");
            //Scanner sc = new Scanner(System.in);
            //if (sc.next().equals("send"))
                send(socket);
            String str = br.readLine();
            System.out.println(str);

            //csr.run();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void send(Socket socket) throws IOException {


        BufferedWriter out; // для записи в сокет
//        BufferedReader in; // чтение из файла
        byte[] bytes;
        String str = new String();

        try (FileReader fr = new FileReader(".\\source_file.txt")) {
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                //System.out.println(line);
                str += line + "\n\r";
                line = br.readLine();
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(str);
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write(str);
        out.newLine();
        out.flush();

        //out.close();

        System.out.println ("Отправили сообщение");

        //socket.close();
    }


}
