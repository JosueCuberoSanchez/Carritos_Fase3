package ucr.ac.cr.ci1320.TerminalNode;

import ucr.ac.cr.ci1320.connection.Connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Universidad de Costa Rica
 * Facultad de Ingeniería
 * Escuela de Ciencias de la Computación e Informática
 * Profesora: Gabriela Barrantes
 * Autores:
 * Abellán Jiménez Mariana B50031
 * Brenes Solano Silvia B41133
 * Cubero Sánchez Josué B42190
 */
public class Client extends Connection {
    private int dispatcherPort;
    private String myIp;
    private HashMap<String, NodeData> nodeDataTable;
    private int port;

    public Client() throws IOException {
        super();
    }

    public Client(int port, String host, HashMap<String, NodeData> nodeDataTable, String myIp) throws IOException {
        super();
        this.nodeDataTable = nodeDataTable;
        this.myIp = myIp;
        this.port = port;
    }

    public Client (String realIp, int port) throws IOException {
        super();
        this.dispatcherPort = port;
    }

    /**
     * Sends the message for the dispatcher
     * @param myRealIp my ip address
     * @param hostNumber my host number
     * @param port real port
     */
    public void dispatcherClient(String myRealIp, String hostNumber, int port) {
        try {
            String newMessage;
            newMessage = "0" + "\n" + "1" + "\n" + hostNumber + "\n"+ myRealIp + "\n" + port + "\n" ;
            super.createSocket("client", 9999, "10.1.130.222"); //Cambiar a IP real
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            System.out.println("El mensaje a enviar es: \n" + Arrays.toString(newMessage.split(","))+"\n");
            this.outServer.writeUTF(newMessage);
            this.cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * In case the destiny ip is outside the network a message will be sent to
     * both routers with the action 2.
     * @param destinyIp to send the message
     * @param message to send
     */
    public void sendMessage(String destinyIp, String message, String realIp){
        try {
            System.out.println("DESTINY IP " + destinyIp + " PORT " + port);
            super.createSocket("client", port, realIp); //Cambiar a IP real
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            String finalMessage = this.myIp + ',' + destinyIp + ',' + message;
            System.out.println("El mensaje a enviar es: \n" + Arrays.toString(finalMessage.split(","))+"\n");
            this.outServer.writeUTF(finalMessage);
            this.cs.close();

            //sendToClient(finalMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

