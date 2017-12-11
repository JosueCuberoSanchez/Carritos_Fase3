package ucr.ac.cr.ci1320.TerminalNode;

public class NodeData {
    private String path; //ip address of the interface
    private String realIp; //real ip address
    private int port; //real port

    public NodeData(String path, String realIp, int port){
        this.path = path;
        this.realIp = realIp;
        this.port = port;
    }

    public String getPath() {return path;}

    public void setPath(String path) {this.path = path;}

    public String getRealIp() {return realIp;}

    public int getPort() {
        return port;
    }
}

