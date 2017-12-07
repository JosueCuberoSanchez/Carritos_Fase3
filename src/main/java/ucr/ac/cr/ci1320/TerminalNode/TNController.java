package ucr.ac.cr.ci1320.TerminalNode;
import ucr.ac.cr.ci1320.TerminalNode.threads.UIThread;
import ucr.ac.cr.ci1320.TerminalNode.Controller;

/**
 * Created by Mariana on 07/12/2017.
 */
public class TNController {
    String[] terminalNodeIps;
    String[] networkIps;
    int nodeNumber;

    public TNController(int nodeNumber){
        this.nodeNumber = nodeNumber;

        terminalNodeIps = new String[]{
                "123.7.2.50",
                "192.118.1.50",
                "10.4.2.50",
                "178.20.2.50",
                "197.197.197.50"
        };

        networkIps = new String[]{
                "123.7.2.0",
                "192.118.1.0",
                "10.4.2.0",
                "178.20.2.0",
                "197.197.197.0"
        };

    }

    public void startTerminalNodes(){
        for (int i = 0; i < this.nodeNumber; i++) {
            Thread UIThread = new Thread(new UIThread(new Controller(terminalNodeIps[i],9000+i),String.valueOf(i)));
        }
    }

}
