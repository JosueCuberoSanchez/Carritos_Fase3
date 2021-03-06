package ucr.ac.cr.ci1320.router;
import ucr.ac.cr.ci1320.connection.Connection;
import ucr.ac.cr.ci1320.dataStructures.BufferList;
import ucr.ac.cr.ci1320.dataStructures.BufferQueue;
import ucr.ac.cr.ci1320.router.threads.QueueThread;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
public class Server extends Connection {

    private Map<String, Interface> ARPTable;
    private Map<String, String> routingTable;
    private BufferList bufferList;
    private BufferQueue bufferQueue;
    private Thread queueThread;

    /**Used when the dispatcher thread starts running.
     *
     * @param ARPTable  table with all the interfaces in the router.
     * @param routingTable corresponds to the IP of all networks around.
     *
     */

    public Server(Map<String, Interface> ARPTable, Map<String, String> routingTable,int listSize){
        this.ARPTable = ARPTable;
        this.routingTable = routingTable;
        this.bufferList = new BufferList(listSize);
        this.bufferQueue = new BufferQueue();
        this.queueThread = new Thread(new QueueThread(this.bufferList,this.bufferQueue,this.ARPTable,this.routingTable));
    }

    public Server(Map<String, Interface> ARPTable, Map<String, String> routingTable){
        this.ARPTable = ARPTable;
        this.routingTable = routingTable;
    }

    public Server(){
    }

    /**
     * Used when a connection is made with the dispatcher, this threads stays awake waiting for a message from the dispatcher.
     * @throws IOException
     */
    public void startServerDispatcher(int port)  {
        String newMessage = "";
        try {
            super.createSocket("server", port, "localhost"); //cambiar por IP real de Dispatcher
            //while (true) {
                System.out.println("\nServidor de dispatcher  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Dispatcher mandó los datos y el router los agarró");
                this.outClient = new DataInputStream(this.cs.getInputStream());
                newMessage = this.outClient.readUTF();
                System.out.println("Mensaje del dispatcher" + newMessage);
                String message[] = newMessage.split("-");
                fillARPTable(message[0]);
                fillRoutingTable(message[1]);
            //}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fills the ARP table with the message given by the dispatcher
     * @param message the table in String format
     */
    public void fillARPTable(String message){
        String messageValues[] = message.split("\n");
        String mapValue[];
        Interface newInterface;
        for(int i = 0; i< messageValues.length; i++){
            mapValue = messageValues[i].split(",");
            newInterface = new Interface(mapValue[1],mapValue[2], Integer.valueOf(mapValue[3]),Integer.valueOf(mapValue[4]));
            this.ARPTable.put(mapValue[0], newInterface);
        }
        System.out.println(ARPTable.size() + "arp");

    }

    /**
     * Fills the routing table with the message given by the dispatcher
     * @param message the table in String format
     */
    public void fillRoutingTable(String message){
        String messageValues[] = message.split("\n");
        String mapValue[];
        for(int i = 0; i< messageValues.length; i++) {
            mapValue = messageValues[i].split(",");
            this.routingTable.put(mapValue[0], mapValue[1]);
        }
        System.out.println(routingTable.size() + "routing");
    }

    //END OF DISPATCHER METHODS.

    /**
     * Used to listen to new messages from all networks around.
     * @param port corresponds to the port where the router is listening for new messages.
     */
    public void startServer(int port){
        /*PRUEBA
        for(int i=0;i<3;i++) {
            String message = "HOLA"+String.valueOf(i);
            this.testDS(message);
        }
        for(int i=0;i<3;i++) {
            if (!this.bufferQueue.isEmpty()) {
                String test = this.bufferQueue.getNextBufferedMessage(this.bufferList);
                System.out.println(test);
            }
        }
        PRUEBA*/
        String newMessage = "";
        try {
            super.createSocket("server", port, "localhost"); //cambiar por IP real de Dispatcher
            while (true) {
                System.out.println("\nServidor general esperando...");
                this.cs = this.ss.accept();
                this.outClient = new DataInputStream(this.cs.getInputStream());
                newMessage = this.outClient.readUTF();
                System.out.println("Llega un nuevo mensaje. "+newMessage);
                this.processMessage(newMessage);

                //if(!this.bufferList.isEmpty()) {
                  //  this.bufferList.requestBuffer(newMessage,this.bufferQueue);
                //}
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*PRUEBA
    public void testDS(String message){
        if(!this.bufferList.isEmpty()) {
            this.bufferList.requestBuffer(message,this.bufferQueue);
        }
    }*/

    public void processMessage(String message){
        String values[] = message.split(",");
        String internInterface = this.routingTable.get(values[1]);
        Interface destiny = this.ARPTable.get(internInterface);
        System.out.println("intern interface " + internInterface + "destiny " +destiny);
        Client client = new Client();
        client.client(destiny, values);
    }




}
