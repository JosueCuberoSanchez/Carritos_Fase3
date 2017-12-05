package ucr.ac.cr.ci1320.router;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.util.Pair;
import ucr.ac.cr.ci1320.connection.Connection;
import ucr.ac.cr.ci1320.router.IpData;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
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

public class Client extends Connection {

    private Pair<String,String> addressPair;
    private Map<String, String> relation;
    private int dispatcherPort;
    private String dispatcherRealIp;
    private Map<String, IpData> ipTable;

    public  Client(Pair<String,String> pair, Map<String, String> relation,Map<String,IpData> ipTable) throws IOException {
        this.addressPair = pair;
        this.relation = relation;
        this.ipTable = ipTable;
    }

    public Client (String realIp, Map<String, IpData> ipTable){
        this.ipTable = ipTable;
        this.dispatcherPort = 9999;
        this.dispatcherRealIp = realIp;
    }

    /**
     * Is used when the router has to send a message to the dispatcher asking for the routing table.
     * @param myRealIp  Corresponds to the real IP of the computer where teh router is running so the Dispatcher can send the answer.
     * @param address This pair contains the fake IP address with the physical address.
     * @param port It corresponds the port where the router is listening so the Dispatcher can send the answer.
     */
    public void dispatcherClient(String myRealIp, Pair<String, String> address, int port) {
        String newMessage;
        newMessage = "1" + "\n " + address.getKey() + "\n" + myRealIp + "\n" + address.getValue() + "\n" + port;
        try {
            super.createSocket("client", this.dispatcherPort, this.dispatcherRealIp);
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(newMessage);
            this.cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used when the router gets a message, checks the receiver and take action depending on it.
     * @param message Corresponds to the message send by the user or the other network the router is connected to.
     */
    public void startClient(String message){
        try {
            String[] arrayMessage = message.split("\n");
            int action = Integer.parseInt(arrayMessage[2]);
            String answerMessage = "";
            IpData ipData = this.ipTable.get(arrayMessage[3]);
            switch (action){
                case 1:
                    if(ipData == null) {
                        if (arrayMessage[3].contains("165")) {
                            answerMessage = this.addressPair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                    '3' + "\n" + this.addressPair.getKey() + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                        } else {
                            answerMessage = arrayMessage[1] + "\n" + arrayMessage[0] + "\n" +
                                    '5' + "\n" + arrayMessage[3] + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                        }
                    }
                    super.createSocket("client",7575,"10.1.131.90"); //CAMBIAR A IP REAL
                    break;
                case 2: //formato del mensaje: Fuenteip + nodoIp + accion + destinoIp + tamano + mensaje
                    if(ipData != null) { //si se la ruta
                        String routing = ipData.getFakeIp() + "\n" +  ipData.getFakePath() + "\n" + ipData.getDistance();
                        answerMessage = this.addressPair.getKey() + "\n" + arrayMessage[0] + "\n" +
                                '4' + "\n" + arrayMessage[3] +"\n" + arrayMessage[4] + "\n" + arrayMessage[5] +"\n" + routing.length() + "\n" + routing;
                    }
                    super.createSocket("client",7575,"10.1.131.90"); //CAMBIAR A IP REAL
                    break;
                default: //caso 0
                    if(ipData==null){ //viene de pablo a Mariana
                        answerMessage = this.addressPair.getKey() + "\n" + "165.8.48.2" + arrayMessage[2] +
                                "\n" + "" + arrayMessage[4].length() + "\n" + arrayMessage[4];
                        System.out.println("recibi mensaje de pablo.");
                        super.createSocket("client", 7575, "10.1.131.90"); //cambiar a ipMariana
                    } else {
                        if(arrayMessage[0].contains("165")){ //se lo mando a pablo
                            answerMessage = this.addressPair.getKey() + "\n" + "25.0.0.8"/*cambiar creo*/ + arrayMessage[2] +
                                    "\n" + arrayMessage[3] + "\n" + arrayMessage[4]; //el mensaje va hacia ellos
                            System.out.println("conexion con Pablo");
                            super.createSocket("client", 7777 , "localhost");
                        } else {
                            if(this.ipTable.get(arrayMessage[1]).equals("CRR2")){//Para mariana
                                answerMessage = this.addressPair.getKey() + "\n" + "165.8.0.48"/*cambiar creo*/ + arrayMessage[2] +
                                        "\n" + arrayMessage[3] + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                                super.createSocket("client", 7575, "10.1.131.90");
                            } else { //para josue
                                answerMessage = this.addressPair.getKey() + "\n" + "165.8.0.6"/*cambiar creo*/ + arrayMessage[2] +
                                        "\n" + arrayMessage[3] + "\n" + arrayMessage[4] + "\n" + arrayMessage[5];
                                super.createSocket("client", 7777, "10.1.130.222");
                            }
                        }
                    }
                    break;
            }
            this.outServer = new DataOutputStream(this.cs.getOutputStream());
            this.outServer.writeUTF(answerMessage);
            System.out.println("Se envio: " + Arrays.toString(answerMessage.split("\n")));
            this.cs.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}