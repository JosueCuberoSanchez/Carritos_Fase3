package ucr.ac.cr.ci1320.TerminalNode;

import ucr.ac.cr.ci1320.TerminalNode.threads.ReadThread;
import ucr.ac.cr.ci1320.TerminalNode.threads.WriteThread;

import java.io.IOException;
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
public class Controller {

    public HashMap<String, NodeData> nodeDataTable;
    private String myIp;
    private int myPort;

    public Controller(String myIp, int myPort) {
        this.nodeDataTable = new HashMap<String, NodeData>(); //table with the physical directions
        this.myIp = myIp;
        this.myPort = myPort;
    }

    /**
     * Checks the user selection and depending on that sends the action message.
     * @param message to send
     */
    public void startController(String[] message) { //destiny ip , message
        try {
            if (!message[0].equalsIgnoreCase("connect")) {
                int port;
                String realIp;
                NodeData r = nodeDataTable.get(message[0]);
                port = r.getPort();
                realIp = r.getRealIp();

                String [] newMessage = new String[2];
                newMessage[0] = realIp;
                newMessage[1] = message[1];
                Thread writeThread = new Thread(new WriteThread(new Client(port, realIp, this.nodeDataTable, myIp), newMessage));
                writeThread.start();

            } else {
                System.out.println("Waiting.....");
            }
            Thread readThread = new Thread(new ReadThread(new Server(this.myPort, this.nodeDataTable)));
            readThread.start(); //starts the Read Thread
            readThread.join(); //waits..
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the connection with the Dispatcher
     * @param DispatcherRealIp is the real Ip from the Dispatcher
     * @param myRealIp my real Ip address
     */
    public void connectToDispatcher(String DispatcherRealIp, String myRealIp, String hostNumber) {
        try {
            Client client = new Client(DispatcherRealIp, 9999);
            client.dispatcherClient(myRealIp, hostNumber, 6666);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the table with the physical addresses of the routers
     * @return the physical table
     */
    public HashMap<String, NodeData> getNodeDataTable() {
        return this.nodeDataTable;
    }
}

