package ucr.ac.cr.ci1320.TerminalNode;

import ucr.ac.cr.ci1320.TerminalNode.threads.DispatcherThread;

import javax.swing.*;
import java.io.IOException;

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

public class UserInterface {
    private Controller controller;
    private String[] values;

    public UserInterface(Controller controller) {
        this.controller = controller;
        this.values = new String[2];
    }

    /**
     * Start the user interface
     */
    public void start(String hostNumber) {
        this.userInterface(hostNumber);
    }

    /**
     * Create the user interface where the user is capable of choosing
     * whether to send a message or receive a message.
     */
    public void userInterface(String hostNumber) {
        //create the connection with the Dispatcher
        Thread dispatcherThread = null;
        try {
            dispatcherThread = new Thread(new DispatcherThread(new Server(this.controller.getNodeDataTable())));
            dispatcherThread.start();
            //TODO CHANGE THE IP DIRECTIONS TO THE REAL ONES!
            this.controller.connectToDispatcher("127.0.0.1", "127.0.0.1", hostNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //start menu
        Object[] posibleValues = {"Connect to network", "Send a message", "Exit"};
        //opens the window with the options
        Object selectedValue = JOptionPane.showInputDialog(null, // choose between send message, connect to the network, exit
                "Choose an option", "Menu",
                JOptionPane.QUESTION_MESSAGE, null,
                posibleValues, posibleValues[0]);;
        while (!selectedValue.equals("Exit")){                             //if the selected value is not exit:
            if (selectedValue.equals("Send a message")) {                  //the user must write the message and direction to send the message
                Object[] ip = {"197.197.197.0", "192.118.1.0", "178.20.2.0", "123.7.2.0", "10.4.2.0"};
                Object selectedIp = JOptionPane.showInputDialog(null, // choose between send message, connect to the network, exit
                        "Choose an option", "Menu",
                        JOptionPane.QUESTION_MESSAGE, null,
                        ip, ip[0]);
                if(ip.equals("197.197.197.0")){
                    this.values[0] = "197.197.197.0";
                }
                else if(ip.equals("192.118.1.0")){
                    this.values[0] = "192.118.1.0";
                }
                else if(ip.equals("178.20.2.0")){
                    this.values[0] = "178.20.2.0";
                }
                else if(ip.equals("123.7.2.0")){
                    this.values[0] = "123.7.2.0";
                }
                else if(ip.equals("10.4.2.0")){
                    this.values[0] = "10.4.2.0";
                }
                this.values[1] = JOptionPane.showInputDialog(
                        "Write the message");
            }else if (selectedValue.equals("Connect to network")) {       //if the user want to connect to the network:
                this.values[0] = "connect";
            }
            this.controller.startController(this.values);                    //starts the controller
            selectedValue = JOptionPane.showInputDialog(null,
                    "Choose an option", "Menu",
                    JOptionPane.QUESTION_MESSAGE, null,
                    posibleValues, posibleValues[0]
            );
        }
    }


    /**
     * Show the received messages
     * @param message received
     * @return boolean to keep receiving messages
     */
    public static boolean showMessage(String message){
        int answer = JOptionPane.showConfirmDialog(null, message, "¿Do you want to keep the connection?", JOptionPane.YES_NO_OPTION);
        return answer != JOptionPane.YES_OPTION; //in case the user wants to keep receiving messages
    }
}