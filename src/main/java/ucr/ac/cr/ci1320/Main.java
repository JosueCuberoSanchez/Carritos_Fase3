package ucr.ac.cr.ci1320;
import ucr.ac.cr.ci1320.TerminalNode.Client;
import ucr.ac.cr.ci1320.TerminalNode.TNController;
import ucr.ac.cr.ci1320.router.Router;
import ucr.ac.cr.ci1320.router.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

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
public class Main {

    public static void main(String[] args) {
       // Controller controller = new Controller();
        //controller.start();

        //Router router = new Router(3, 1, "10.1.130.141", 7777);
        //router.startController();
        //Router router = new Router(3, 1, "127.0.0.1", 7777);
        //router.startController();
        String dispatcherIp = "10.1.130.222";
        String myRealIp = "10.1.130.207";
        TNController terminalNode = new TNController(dispatcherIp, myRealIp);
        terminalNode.startTerminalNodes();

        /*try {
            Client client = new Client("client", 5503, "10.1.131.37");
            client.serverOutStream = new DataOutputStream(client.clientSocket.getOutputStream());
            client.serverOutStream.writeUTF("hola");
            client.clientSocket.close();
            //  protected DataOutputStream serverOutStream;
            //protected DataInputStream clientOutStream;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
