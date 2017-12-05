package ucr.ac.cr.ci1320.router.threads;

import ucr.ac.cr.ci1320.router.TestServer;

/**
 * Created by josue on 26/11/17.
 */
public class LightsThread implements Runnable {
    public LightsThread(){

    }

    public void run(){
        TestServer ts = new TestServer();
        ts.listen();
    }
}