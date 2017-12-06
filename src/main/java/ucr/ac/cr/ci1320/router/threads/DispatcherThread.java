package ucr.ac.cr.ci1320.router.threads;

import ucr.ac.cr.ci1320.router.Server;

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
public class DispatcherThread implements Runnable {
    private Server server;
    private int port;

    public DispatcherThread(Server server,int port){
        this.server = server;
        this.port = port;
    }

    public void run(){
        try {
            this.server.startServerDispatcher(this.port);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

