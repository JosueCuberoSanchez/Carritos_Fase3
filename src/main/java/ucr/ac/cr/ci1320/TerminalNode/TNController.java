package ucr.ac.cr.ci1320.TerminalNode;
import ucr.ac.cr.ci1320.TerminalNode.threads.UIThread;
import ucr.ac.cr.ci1320.TerminalNode.Controller;

/**
 * Created by Mariana on 07/12/2017.
 */
public class TNController {
    String[] terminalNodeIps;
    int numberofNodes;
    int nodeNumer = 3;


    public TNController(int nodeNumber){
        this.numberofNodes = nodeNumber;

        terminalNodeIps = new String[]{
                "197.197.197.50",//(1)
                "192.118.1.50",  //(2)
                "178.20.2.50",   //(3)
                "123.7.2.50",    //(4)
                "10.4.2.50",     //(5)

        };
    }

    public void startTerminalNodes(){
        //for (int i = 0; i < this.numberofNodes; i++) {
            Thread uIThread = new Thread(new UIThread(new Controller("123.7.2.50",9000+nodeNumer),String.valueOf(nodeNumer+1)));
            uIThread.run();
        //}
    }

}
