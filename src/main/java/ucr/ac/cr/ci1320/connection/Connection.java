package ucr.ac.cr.ci1320.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

public class Connection {
    private int PORT;
    private String HOST;
    public ServerSocket ss;
    public Socket cs;
    protected DataOutputStream outServer;
    public DataInputStream outClient;

    public Connection(){}

    /**
     * Used every time the router needs to make a connection.
     * @param type Corresponds a value withing "client" or "server".
     * @param PORT Corresponds to the port where the server is listening in or the client is going to make a connection with.
     * @param HOST Corresponds to the real IP of the host.
     * @throws IOException
     */
    public void createSocket(String type, int PORT, String HOST) throws IOException{
        this.PORT = PORT;
        this.HOST = HOST;
        if (type.equalsIgnoreCase("server")) {
            this.ss = new ServerSocket(this.PORT);
            this.cs = new Socket();
        }
        else {
            this.cs = new Socket(HOST, PORT);
        }
    }
}
