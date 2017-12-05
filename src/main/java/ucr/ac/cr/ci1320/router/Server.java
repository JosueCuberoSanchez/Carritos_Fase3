package ucr.ac.cr.ci1320.router;

import javafx.util.Pair;
import ucr.ac.cr.ci1320.connection.Connection;
import ucr.ac.cr.ci1320.router.Client;
import ucr.ac.cr.ci1320.router.IpData;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
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
    private Client client;
    private Pair<String,String> pair;
    private Map<String,String> relation;
    private Map<String, IpData> ipTable;


    public Server(Pair<String,String> pair, Map<String,String> relation,Map<String,IpData> ipTable) throws IOException {
        this.pair = pair;
        this.relation = relation;
        this.ipTable = ipTable;
    }

    public Server (Map<String, IpData> ipTable){
        this.ipTable = ipTable;
    }


    /**
     * Used in the thread that is always listening, calls the client if a message is received.
     * @throws IOException
     */
    public void startServer() throws IOException{
        super.createSocket("server", 6000, "localhost");
        try {
            while(true){
                System.out.println("\nServidor  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Cliente conectado en el servidor ");
                this.outClient = new DataInputStream(this.cs.getInputStream());
                String newMessage = this.outClient.readUTF();
                System.out.println(newMessage);
                System.out.println("Se recibio: "+ Arrays.toString(newMessage.split("\n"))+"\n");
                this.client = new Client(this.pair, this.relation,this.ipTable);
                this.client.startClient(newMessage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used when a connection is made with the dispatcher, this threads stays awake waiting for a message from the dispatcher.
     * @throws IOException
     */
    public void startServerDispatcher() throws IOException{
        super.createSocket("server", 3000, "localhost");
        try {
            while(true) {
                System.out.println("\nServidor de dispatcher  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Dispatcher manda los datos");
                this.outClient = new DataInputStream(this.cs.getInputStream());
                String newMessage = this.outClient.readUTF();
                this.fillTable(newMessage);
                this.printTable();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Used to print the ip table for testing purposes
     */
    public void printTable(){
        Iterator iterator = this.ipTable.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry pair = (Map.Entry) iterator.next();
            IpData ipData = (IpData) pair.getValue();
            System.out.println(pair.getKey()+"--"+ipData.getFakeIp()+"--"+ipData.getRealIp()+"--"
                    +ipData.getFakePath()+"--"+ipData.getDistance()+"--"+ipData.getPort());
        }
    }

    /**
     * Used once the answer from the dispatcher is received. It transforms the data in the string into meaningful data and save it in the routing table.
     * @param message
     */
    public void fillTable(String message){
        String[] entries = message.split("\n");
        for(int i = 0; i < entries.length;  i++){
            String[] data = entries[i].split(",");
            ipTable.put(data[0], new IpData(data[1], data[2],data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5])));
        }
    }
}