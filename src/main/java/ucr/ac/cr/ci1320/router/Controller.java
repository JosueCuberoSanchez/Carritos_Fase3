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
public class Controller {
    private Map<String,String> oneToOneRelation;
    private String myIpAddress;
    private String myPhysicalAddress;
    private Map<String, IpData> ipTable;

    public Controller(){
        this.oneToOneRelation = new HashMap<String,String>();
        this.oneToOneRelation.put("165.8.0.6","CRR6");//josue
        this.oneToOneRelation.put("165.8.0.48","CRR2"); //mariana
        //el propio se tiene como atributo
        this.myIpAddress = "165.8.25.6";
        this.myPhysicalAddress = "CRR4";
        this.ipTable = new HashMap<String,IpData>();
    }

    /**
     * Starts the router.
     * @throws IOException
     */
    public void startController() throws IOException {
        Thread dispatcherThread = new Thread(new DispatcherThread(new Server(this.ipTable)));
        dispatcherThread.start();
        Thread thread = new Thread(new ReadThread(new Server(new Pair<String,String>(this.myIpAddress,this.myPhysicalAddress), this.oneToOneRelation,this.ipTable)));
        thread.start();
        this.conectToDisptacher("10.1.131.90", "10.1.130.141"); //CAMBIAR
    }

    /**
     * Starts the communication with the dispatcher.
     * @param DispatcherRealIp Corresponds to the real IP of the computer where the dispatcher is running.
     * @param myRealIp Corresponds to the real IP where the router is running.
     */
    private void conectToDisptacher(String DispatcherRealIp, String myRealIp){
        Client client = new Client(DispatcherRealIp, this.ipTable);
        Pair<String, String> address = new Pair<String,String>(this.myIpAddress, this.myPhysicalAddress);
        client.dispatcherClient(myRealIp,address, 3000);
    }
}
