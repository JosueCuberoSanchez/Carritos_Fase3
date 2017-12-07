package ucr.ac.cr.ci1320.TerminalNode;

public class NodeData {
    private String path;
    private String realIp;
    private int port;

    public NodeData(String path, String realIp, int port){
        this.path = path;
        this.realIp = realIp;
        this.port = port;
    }

    public String getPath() {return path;}

    public void setPath(String path) {this.path = path;}

    public String getRealIp() {
        return realIp;
    }

    public int getPort() {
        return port;
    }
}

