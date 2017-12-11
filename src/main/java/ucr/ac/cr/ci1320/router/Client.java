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
        super();
    }

    /**
     * Is used when the router has to send a message to the dispatcher asking for the routing table.
     */
    public void dispatcherClient(int interfacesQuantity, int host, String myRealIp, int port) {
        String newMessage;
        newMessage = "1" + "\n" + Integer.toString(interfacesQuantity)+ "\n" + Integer.toString(host)+ "\n" +  myRealIp+ "\n" + Integer.toString(port);
        try {
            super.createSocket("client", 9999, "10.1.130.222"); //Cambiar a IP real
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
            newMessage =  destinyInterface.getExternInterface() + "," + values[1] + "," + values[2];
            System.out.println("new message is "+newMessage);
            System.out.println("Sending to "+destinyInterface.getExternPort()+" " +destinyInterface.getExternIp());
            super.createSocket("client", destinyInterface.getExternPort(), destinyInterface.getExternIp()); //Cambiar a IP real
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(newMessage);
            System.out.println("envie el mensaje");
            this.cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}