package ucr.ac.cr.ci1320.router;

import ucr.ac.cr.ci1320.connection.Connection;

import java.io.DataOutputStream;
import java.io.IOException;

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

    public Client (){

    }

    /**
     * Is used when the router has to send a message to the dispatcher asking for the routing table.
     */
    public void dispatcherClient(int interfacesQuantity, int host, String myRealIp, int port) {
        String newMessage;
        newMessage = "1" + "," + Integer.toString(interfacesQuantity)+ "," + Integer.toString(host)+ "," +  myRealIp+ "," + Integer.toString(port);
        try {
            super.createSocket("client", 9999, "localhost"); //Cambiar a IP real
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(newMessage);
            this.cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new client to send the received message.
     * @param destinyInterface the interface wich has to send the message.
     * @param values the original received message.
     */
    public void client(Interface destinyInterface, String values[]){
        String newMessage;
        try {
            newMessage = values[1] + destinyInterface.getExternInterface() + values[2];
            super.createSocket("client", destinyInterface.getPort(), "localhost"  /*destinyInterface.getExternIp()*/); //Cambiar a IP real
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(newMessage);
            this.cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}