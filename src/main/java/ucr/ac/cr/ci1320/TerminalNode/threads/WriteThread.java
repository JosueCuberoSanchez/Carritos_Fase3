package ucr.ac.cr.ci1320.TerminalNode.threads;

import ucr.ac.cr.ci1320.TerminalNode.Client;

import java.util.HashMap;
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
public class WriteThread implements Runnable{
    private Client client;
    private String[] response;

    /**
     * Creates the write thread
     * @param client
     * @param response array that contains the destiny ip and the message
     */
    public WriteThread(Client client, String[] response){
        this.client = client;
        this.response = response;
    }

    public void run() {
       // this.client.startClient();
        this.client.sendMessage(response[0], response[1], response[2]);
    }
}

