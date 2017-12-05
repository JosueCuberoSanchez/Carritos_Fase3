package ucr.ac.cr.ci1320.router;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by josue on 25/11/17.
 */
public class TestServer {

    public void write() throws IOException{
        System.out.println("Input the host");
        Scanner scan = new Scanner(System.in);
        String host = scan.next();
        InetAddress address = InetAddress.getByName(host);
        Socket socket = new Socket(address, 2626);
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        String sendMessage = "165.0.2.6\n200.8.1.43\n1\n127.12.13.254\n67\nHOLA SOY EL CLIENTE\n";
        bw.write(sendMessage);
        bw.flush();
        //System.out.println("Message sent to the server : "+sendMessage);
    }

    public void listen() {
        //while (true) {
        try{
            ServerSocket serverSocket = new ServerSocket(5555);
            //Server is running always. This is done using this while(true) loop
            while(true){
                //Reading the message from the client
                Socket socket = serverSocket.accept();
                System.out.println("Client has connected!");
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String clientMessage = "";
                String line;
                int counter = 0;
                while(counter <= 5){
                    line = br.readLine();
                    clientMessage = clientMessage + line + "\n";
                    counter++;
                }
                System.out.println("Message received from client is "+clientMessage);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //}
    }

}