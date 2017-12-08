package ucr.ac.cr.ci1320.TerminalNode;

import ucr.ac.cr.ci1320.TerminalNode.threads.UIThread;
import ucr.ac.cr.ci1320.connection.Connection;

import java.io.*;
import java.net.Socket;
import java.util.*;

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
    private HashMap<String, NodeData> nodeDataTable;
    private Client client;

    public Server(int port, HashMap<String, NodeData> nodeDataTable) throws IOException{
        super();
        super.createSocket("server", port, "localhost");
        this.nodeDataTable = nodeDataTable;
    }
    /*
     * Used with the Dispatcher
     */
    public Server(HashMap<String, NodeData> nodeDataTable) throws IOException {
        super();
        super.createSocket("server", 6666, "localhost");
        this.nodeDataTable = nodeDataTable;
    }

    /**
     *  Receives the answer from the router and sends the final message with action 0
     */
    public void startServer(){
        try{
            boolean done = false;
            while(done == false) {
                cs = ss.accept();
                this.outClient = new DataInputStream(this.cs.getInputStream());
                String message = this.outClient.readUTF();
                System.out.println("Received from "+cs.getPort()+":\n"+ Arrays.toString((message.split(",")))+"\n");
                String[] arrayMessage = message.split(",");
                String answerMessage = "Se recibe:\n"+arrayMessage[2]+"\nde: "+arrayMessage[0];// 0
                done = UIThread.showMessage(answerMessage); //calls the method in the User Interface
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the connection with the dispatcher and fills the table
     */
    public void startDispatcher(){
        try {
            while(true) {
                System.out.println("\nServidor de dispatcher  esperando...");
                this.cs = this.ss.accept();
                System.out.println("Cliente de dispatcher conectado en el servidor");
                this.outClient = new DataInputStream(this.cs.getInputStream());
                String newMessage = this.outClient.readUTF();
                System.out.println(newMessage);
                this.fillDispatcherTable(newMessage);
                Iterator iterator = this.nodeDataTable.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry pair = (Map.Entry) iterator.next();
                    NodeData nodeData = (NodeData) pair.getValue();
                    System.out.println(pair.getKey()+","+ nodeData.getPath()+","+ nodeData.getRealIp()+","+ nodeData.getPort());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fills the table with the physical addresses of the routers sent by the Dispatcher
     * @param message to send
     */
    public void fillDispatcherTable(String message){
        String[] megaData = message.split("\n");

/*        for (int i = 0; i < megaData.length; i++){
            String[] datas = megaData[i].split(","); //destiny + path + realIp + port
            NodeData nodeData = new NodeData(datas[1], datas[2], Integer.valueOf(datas[3]));
            this.nodeDataTable.put(datas[0], nodeData); //add 1st direction
        }*/


        String[] datas = megaData[0].split(","); //destiny + path + realIp + port
        NodeData nodeData = new NodeData(datas[1], datas[2], Integer.valueOf(datas[3]));
        this.nodeDataTable.put(datas[0], nodeData); //add 1st direction

        String[] datas2 = megaData[1].split(",");
        NodeData nodeData2 = new NodeData(datas2[1], datas2[2],Integer.valueOf(datas2[3]));
        this.nodeDataTable.put(datas2[0], nodeData2); //add 2nd direction

        String[] datas3 = megaData[2].split(",");
        NodeData nodeData3 = new NodeData(datas3[1],datas3[2], Integer.valueOf(datas3[3]));
        this.nodeDataTable.put(datas3[0], nodeData3); //add 3rd direction

        String[] datas4 = megaData[3].split(",");
        NodeData nodeData4 = new NodeData(datas4[1], datas4[2], Integer.valueOf(datas4[3]));
        this.nodeDataTable.put(datas4[0], nodeData4); //add 4rd direction

        String[] datas5 = megaData[4].split(",");
        NodeData nodeData5 = new NodeData(datas5[1], datas5[2], Integer.valueOf(datas5[3]));
        this.nodeDataTable.put(datas5[0], nodeData5); //add 5th direction
    }

}

