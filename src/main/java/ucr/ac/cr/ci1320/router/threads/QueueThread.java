package ucr.ac.cr.ci1320.router.threads;

import ucr.ac.cr.ci1320.dataStructures.BufferList;
import ucr.ac.cr.ci1320.dataStructures.BufferQueue;
import ucr.ac.cr.ci1320.router.Client;
import ucr.ac.cr.ci1320.router.Interface;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class QueueThread implements Runnable {
    private Map<String, Interface> ARPTable;
    private Map<String, String> routingTable;
    private BufferList bufferList;
    private BufferQueue bufferQueue;

    public QueueThread(BufferList bufferList, BufferQueue bufferQueue,Map<String, Interface> ARPTable, Map<String, String> routingTable){
        this.ARPTable = ARPTable;
        this.routingTable = routingTable;
        this.bufferList = bufferList;
        this.bufferQueue = bufferQueue;
    }

    public void run(){
        while(true){
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!this.bufferQueue.isEmpty()) {
                this.processMessage(this.bufferQueue.getNextBufferedMessage(this.bufferList));
            }
        }
    }

    /**
     * Processes the new message and send it to its destiny/intermediate.
     * @param message the message received to process.
     */
    public void processMessage(String message){
        String values[] = message.split(",");
        String internInterface = this.routingTable.get(values[1]);
        Interface destiny = this.ARPTable.get(internInterface);
        Client client = new Client();
        client.client(destiny, values);
    }
}
