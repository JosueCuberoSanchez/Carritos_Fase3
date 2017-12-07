package ucr.ac.cr.ci1320.TerminalNode.threads;

import ucr.ac.cr.ci1320.TerminalNode.Server;

public class DispatcherThread implements Runnable{
    private Server server;

    /**
     * Creates the dispatcher thread
     * @param server
     */
    public DispatcherThread(Server server){
        this.server = server;
    }
    @Override
    public void run() { this.server.startDispatcher(); }
}