package ucr.ac.cr.ci1320.TerminalNode.threads;
import ucr.ac.cr.ci1320.TerminalNode.Server;

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


public class DispatcherThread implements Runnable{
    private Server server;

    /**
     * Creates the dispatcher thread
     * @param server
     */
    public DispatcherThread(Server server){
        this.server = server;
    }

    public void run() { this.server.startDispatcher(); }
}