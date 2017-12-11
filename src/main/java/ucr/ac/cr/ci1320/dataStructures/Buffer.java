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
    private boolean isTaken;

    public Buffer(String message){
        this.message = message;
        this.nextBuffer = null;
        this.isTaken = false;
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

    /**
     * If buffer is not taken, take it but return false so Thread can pass the if statement. If its taken return that is taken.
     * @return the isTaken state.
     */
    public synchronized boolean isTaken() {
        boolean temporal = this.isTaken;
        if(!this.isTaken){
            this.isTaken = true;
        }
        return temporal;
    }

    public void setTaken(boolean state){
        this.isTaken = state;
    }
}

