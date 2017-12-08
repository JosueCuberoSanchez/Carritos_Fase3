package ucr.ac.cr.ci1320.router;
import ucr.ac.cr.ci1320.router.threads.DispatcherThread;
import ucr.ac.cr.ci1320.router.threads.ReadThread;
import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class Router implements Runnable{

    private int interfacesQuantity;
    private int hostNumber;
    private Map<String, Interface> ARPTable;
    private Map<String, String> routingTable;
    private String realIp;
    private int dispatcherPort;
    private int listSize;


    public Router(int interfacesQuantity,  int host, String realIp, int dispatcherPort, int listSize){
        this.ARPTable = new HashMap<String, Interface>();
        this.routingTable = new HashMap<String, String>();
        this.interfacesQuantity = interfacesQuantity;
        this.hostNumber = host;
        this.realIp = realIp;
        this.dispatcherPort = dispatcherPort;
        this.listSize = listSize;
    }

    public void run(){
        this.startController();
    }

    /**
     * Starts the router.
     * @throws IOException
     */
    public void startController() {

        Thread dispatcherThread = new Thread(new DispatcherThread(new Server(this.ARPTable, this.routingTable), this.dispatcherPort ));
        dispatcherThread.start();
        this.connectToDispatcher();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Interface newInterface;
        for (Map.Entry<String, Interface> entry : this.ARPTable.entrySet()) {

            if(!entry.getKey().contains(".50")) {
                newInterface = entry.getValue();
                System.out.println(entry.getKey() + "-" + newInterface.getMyPort());
                Thread readThread = new Thread(new ReadThread(new Server(this.ARPTable, this.routingTable, this.listSize), newInterface.getMyPort()));
                readThread.start();
            }
        }
    }
    /**
     * Starts the communication with the dispatcher.
     */
    private void connectToDispatcher(){
        Client client = new Client();
        client.dispatcherClient(this.interfacesQuantity, this.hostNumber, this.realIp, this.dispatcherPort);
    }

}
