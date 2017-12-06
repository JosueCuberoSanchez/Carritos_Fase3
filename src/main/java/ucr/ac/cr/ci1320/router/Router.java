package ucr.ac.cr.ci1320.router;

import javafx.util.Pair;
import ucr.ac.cr.ci1320.router.Client;
import ucr.ac.cr.ci1320.router.IpData;
import ucr.ac.cr.ci1320.router.Server;
import ucr.ac.cr.ci1320.router.threads.DispatcherThread;
import ucr.ac.cr.ci1320.router.threads.ReadThread;

import java.io.IOException;
import java.util.HashMap;
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
public class Router implements Runnable{
    private Map<String, IpData> ipTable;
    private int interfaces;
    private int host;

    public Router(int interfaces, int i){
        this.ipTable = new HashMap<String,IpData>();
        this.interfaces = interfaces;
        this.host = i;
    }

    public void run(){
        try {
            this.startController();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Starts the router.
     * @throws IOException
     */
    public void startController() throws IOException {
        Thread dispatcherThread = new Thread(new DispatcherThread(new Server(this.ipTable),this.host+5000));
        dispatcherThread.start();
        //Thread thread = new Thread(new ReadThread(new Server(new Pair<String,String>(this.myIpAddress,this.myPhysicalAddress), this.oneToOneRelation,this.ipTable)));
        //thread.start();
        this.connectToDispatcher();
    }

    /**
     * Starts the communication with the dispatcher.
     */
    private void connectToDispatcher(){
        Client client = new Client(this.ipTable, this.interfaces, this.host);
        //digamos que la dir fisica es el host
        client.dispatcherClient(this.host+5000);
    }
}
