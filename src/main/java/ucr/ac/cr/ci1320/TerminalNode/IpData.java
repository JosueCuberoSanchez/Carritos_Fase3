package ucr.ac.cr.ci1320.TerminalNode;

public class IpData {
    public String routerIp; //router ip
    public String path; //path to get to the chosen destiny ip
    public int distance; //distance to get to the chosen destiny ip

    IpData(String dIp, String p, int dis){
        this.routerIp = dIp;
        this.path = p;
        this.distance = dis;
    }

    public String getRouterIp() {
        return routerIp;
    }

    public String getPath() {
        return path;
    }

    public int getDistance() {
        return distance;
    }

}

