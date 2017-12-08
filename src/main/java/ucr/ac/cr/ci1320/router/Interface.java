package ucr.ac.cr.ci1320.router;

import ucr.ac.cr.ci1320.dataStructures.BufferList;
import ucr.ac.cr.ci1320.dataStructures.BufferQueue;

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

    private int myPort;
    private String externInterface;
    private String externIp;
    private int externPort;



    public Interface(String externInterface, String externIp, int externPort, int myPort) {
        this.externInterface= externInterface;
        this.externIp = externIp;
        this.myPort = myPort;
        this.externPort = externPort;
    }

    public String getExternInterface() {
        return this.externInterface;
    }

    public String getExternIp() {
        return this.externIp;
    }

    public int getMyPort() {
        return this.myPort;
    }

    public int getExternPort() {
        return this.externPort;
    }
}

