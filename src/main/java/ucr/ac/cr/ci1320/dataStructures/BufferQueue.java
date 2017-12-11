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

public class BufferQueue {
    private Buffer startBuffer;

    public BufferQueue(){
        this.startBuffer = null;
    }

    /**
     * Returns true if queue is empty, false otherwise.
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty(){
        if(this.startBuffer != null){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adds a new buffer to the queue.
     * @param buffer the new buffer to be added.
     */
    public void addBuffer(Buffer buffer){
        Buffer newBuffer = new Buffer(buffer.getMessage());
        if(this.startBuffer == null){ //si es el primero
            this.startBuffer = newBuffer;
        } else {
            Buffer temporal = this.startBuffer;
            while(temporal.getNextBuffer() != null){
                temporal = temporal.getNextBuffer();
            }
            temporal.setNextBuffer(newBuffer); // sino agregamos de ultimo
        }
    }

    /**
     * Gets the next buffer to be processed.
     * @param bufferList the buffer list to be given a node after queue process the message.
     * @return the buffered message.
     */
    public String getNextBufferedMessage(BufferList bufferList){ //falta agregar el nodo a la lista
        String message = null;
        if(this.startBuffer != null) {
            message = this.startBuffer.getMessage();
            bufferList.addNode(this.startBuffer);
            this.startBuffer = this.startBuffer.getNextBuffer();
        } else {
            System.out.println("Returning null. No buffered messages found");
        }
        return message;
    }
}
