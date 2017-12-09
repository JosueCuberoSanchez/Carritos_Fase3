package ucr.ac.cr.ci1320.TerminalNode.threads;
import ucr.ac.cr.ci1320.TerminalNode.Controller;
import ucr.ac.cr.ci1320.TerminalNode.Server;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Mariana on 07/12/2017.
 */
public class UIThread implements Runnable {
    private Controller controller;
    private String[] values;
    private String hostNumber;

    public UIThread(Controller controller, String hostNumber) {
        this.controller = controller;
        this.values = new String[2];
        this.hostNumber = hostNumber;
    }

    /**
     * Start the user interface
     */
    public void run() {
        this.userInterface();
    }

    /**
     * Create the user interface where the user is capable of choosing
     * whether to send a message or receive a message.
     */
    public void userInterface() {
        //create the connection with the Dispatcher
        Thread dispatcherThread = null;
        try {
            dispatcherThread = new Thread(new DispatcherThread(new Server(this.controller.getNodeDataTable())));
            dispatcherThread.start();
            //TODO CHANGE THE IP DIRECTIONS TO THE REAL ONES!
            this.controller.connectToDispatcher("10.1.130.222", "10.1.131.37", this.hostNumber);
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
                if(selectedIp.equals("197.197.197.0")){
                    this.values[0] = "197.197.197.0";
                }
                else if(selectedIp.equals("192.118.1.0")){
                    this.values[0] = "192.118.1.0";
                }
                else if(selectedIp.equals("178.20.2.0")){
                    this.values[0] = "178.20.2.0";
                }
                else if(selectedIp.equals("123.7.2.0")){
                    this.values[0] = "123.7.2.0";
                }
                else if(selectedIp.equals("10.4.2.0")){
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
