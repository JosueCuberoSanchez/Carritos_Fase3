package ucr.ac.cr.ci1320;
import ucr.ac.cr.ci1320.TerminalNode.TNController;
import ucr.ac.cr.ci1320.router.Client;
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

        //TNController terminalNode = new TNController(1);
        //terminalNode.startTerminalNodes();

        try {
            Client client = new Client();
            client.createSocket("client", 8000, "10.1.131.37"); //Cambiar a IP real
            client.outServer = new DataOutputStream(client.cs.getOutputStream());
            //System.out.println("El mensaje a enviar es: \n" + Arrays.toString(newMessage.split(","))+"\n");
            client.outServer.writeUTF("holi");
            client.cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    Server server = new Server();
    String newMessage = "";
            try {
        server.createSocket("server", 5503, "localhost"); //cambiar por IP real de Dispatcher
        while (true) {
            System.out.println("\nServidor de dispatcher  esperando...");
            server.cs = server.ss.accept();
            server.outClient = new DataInputStream(server.cs.getInputStream());
            newMessage = server.outClient.readUTF();
            System.out.println("Mensaje del dispatcher" + newMessage);

        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}*/
    }


