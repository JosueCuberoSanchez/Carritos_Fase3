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

public class Interface {

    private String externInterface;
    private String externIp;
    private int port;


    public Interface(String externInterface, String externIp, int port) {
        this.externInterface= externInterface;
        this.externIp = externIp;
        this.port = port;
    }

    public String getExternInterface() {
        return this.externInterface;
    }

    public String getExternIp() {
        return this.externIp;
    }

    public int getPort() {
        return this.port;
    }
}

