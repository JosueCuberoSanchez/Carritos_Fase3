package ucr.ac.cr.ci1320.dataStructures;
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
public class Buffer {
    private String message;
    private Buffer nextBuffer;

    public Buffer(String message){
        this.message = message;
        this.nextBuffer = null;
    }

    public String getMessage() {
        return this.message;
    }

    public Buffer setMessage(String message) {
        this.message = message;
        return this;
    }

    public Buffer getNextBuffer(){
        return this.nextBuffer;
    }

    public void setNextBuffer(Buffer nextBuffer){
        this.nextBuffer = nextBuffer;
    }
}

