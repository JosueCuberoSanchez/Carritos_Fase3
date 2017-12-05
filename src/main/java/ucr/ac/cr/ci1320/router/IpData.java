package ucr.ac.cr.ci1320.router;


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
public class IpData {
    private String realIp;
    private String fakeIp;
    private String fakePath;
    private int distance;
    private int port;

    public IpData(String realIp, String destinyIp, String fakePath, int distance,int port){
        this.realIp = realIp;
        this.fakeIp = destinyIp;
        this.fakePath = fakePath;
        this.distance = distance;
        this.port = port;
    }

    public String getRealIp() {
        return this.realIp;
    }

    public String getFakeIp() {
        return this.fakeIp;
    }

    public String getFakePath() {
        return this.fakePath;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getPort(){ return this.port; }
}
