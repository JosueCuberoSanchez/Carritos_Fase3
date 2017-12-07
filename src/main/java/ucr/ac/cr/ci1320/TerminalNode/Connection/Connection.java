package ucr.ac.cr.ci1320.TerminalNode.Connection;

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
    private int port;
    private String host;
    protected ServerSocket serverSocket;
    protected Socket clientSocket;
    protected DataOutputStream serverOutStream;
    protected DataInputStream clientOutStream;

    public Connection(String type, int port, String host ) throws IOException{
        this.port = port;
        this.host = host;

        if( type.equalsIgnoreCase("server")){
            serverSocket = new ServerSocket(this.port);
            clientSocket = new Socket();
        }
        else{
            clientSocket = new Socket(host, port);
        }
    }
}
