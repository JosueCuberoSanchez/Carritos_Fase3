package ucr.ac.cr.ci1320.dataStructures;

import ucr.ac.cr.ci1320.router.Server;
import ucr.ac.cr.ci1320.router.threads.ReadThread;

/**
 * Created by josue on 07/12/17.
 */
public class MainPruebas {
    public static void main(String[] args) {
            Thread thread = new Thread(new ReadThread(new Server(null, null, 5),9000));
            thread.start();
    }
}
