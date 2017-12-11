package ucr.ac.cr.ci1320.TerminalNode;
import ucr.ac.cr.ci1320.TerminalNode.threads.UIThread;
import ucr.ac.cr.ci1320.TerminalNode.Controller;

/**
 * Created by Mariana on 07/12/2017.
 */
public class TNController {
    String[] terminalNodeIps;
    String dispatcherIp, myRealIp;

    /**
     * Controller that creates the UIThread to create the terminal node
     */
    public TNController(String dispatcherIp, String myRealIp){
        terminalNodeIps = new String[]{
                "197.197.197.50",//(1)
                "192.118.1.50",  //(2)
                "178.20.2.50",   //(3)
                "123.7.2.50",    //(4)
                "10.4.2.50",     //(5)

        };
        this.dispatcherIp = dispatcherIp;
        this.myRealIp = myRealIp;
    }

    public void startTerminalNodes(){
        //for (int i = 0; i < this.numberofNodes; i++) {
            Thread uIThread = new Thread(new UIThread(new Controller("123.7.2.50",9004),String.valueOf(4),dispatcherIp, myRealIp));
            uIThread.run();
        //}
    }

}
