package ucr.ac.cr.ci1320.TerminalNode.threads;

import ucr.ac.cr.ci1320.TerminalNode.Server;

public class ReadThread implements Runnable{
    private Server server;

    public ReadThread(Server server){
        this.server = server;
    }
    @Override
    public void run() {
        server.startServer();
    }
}

