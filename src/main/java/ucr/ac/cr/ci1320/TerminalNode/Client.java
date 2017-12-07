package ucr.ac.cr.ci1320.TerminalNode;

import javafx.util.Pair;
import ucr.ac.cr.ci1320.TerminalNode.Connection.ConnectionType;

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
public class Client extends ucr.ac.cr.ci1320.TerminalNode.Connection.Connection {
    private String dispatcherRealIp = "127.0.0.1"; //TODO change to real ip
    private int dispatcherPort;
    private String myIp;
    private HashMap<String, NodeData> nodeDataTable;


    public Client(ConnectionType type, int port, String host, HashMap<String, NodeData> nodeDataTable, String myIp) throws IOException {
        super("client", port, host);
        this.nodeDataTable = nodeDataTable;
        this.myIp = myIp;
    }

    public Client (String realIp, int port) throws IOException {
        super("client", port, realIp);
        this.dispatcherPort = port;
    }

    public void startClient(){
        try {
            serverOutStream = new DataOutputStream(clientSocket.getOutputStream());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sends the message for the dispatcher
     * @param myRealIp my ip address
     * @param hostNumber my host number
     * @param port real port
     */
    public void dispatcherClient(String myRealIp, String hostNumber, int port) {
        String newMessage;
        newMessage = "0" + "\n" + "1" + "\n" + hostNumber + "\n"+ myRealIp + "\n" + port + "\n" ;
        try {
            this.serverOutStream = new DataOutputStream(this.clientSocket.getOutputStream());
            this.serverOutStream.writeUTF(newMessage);
            this.clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * In case the destiny ip is outside the network a message will be sent to
     * both routers with the action 2.
     * @param destinyIp direccion del destino a la cual se envia el mensaje
     * @param message mensaje a enviar
     */
    public void sendMessage(String destinyIp, String message){
        String finalMessage = this.myIp + ',' + destinyIp + ',' + message;
        sendToClient(finalMessage);
    }

    /**
     * Sends the message
     * @param input is the message to be sent
     */
    public void sendToClient(String input){
        System.out.println("El mensaje a enviar es: \n" + Arrays.toString(input.split(","))+"\n");
        try {
            serverOutStream.writeUTF(input);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

