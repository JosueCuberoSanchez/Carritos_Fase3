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


public class BufferList {
    private Buffer startBuffer;

    /**
     * Constructs a list with size nodes.
     * @param size the desired quantity of nodes(buffers).
     */
    public BufferList(int size){
        this.startBuffer = new Buffer(String.valueOf(1)); //inicia el primer buffer
        Buffer temporal = this.startBuffer; //buffer temporal para iterar
        int counter = 2;
        while(counter <= size){ //hasta que se creen todos los buffers solicitados (vacios)
            temporal.setNextBuffer(new Buffer(String.valueOf(counter))); //mensaje con un numerillo por el momento
            temporal = temporal.getNextBuffer();
            counter++;
        }
    }

    /**
     * Returns true if list is empty, false otherwise.
     * @return
     */
    public boolean isEmpty(){
        if(this.startBuffer != null){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Request a buffer for a message, if no buffer is available message is lost, if there are buffers available, store it and send buffer to queue.
     * @param message the message.
     * @param bufferQueue the buffer queue.
     */
    public void requestBuffer(String message,BufferQueue bufferQueue){
        if(!this.startBuffer.isTaken()){ //si es el primero
            this.startBuffer.setMessage(message);
            bufferQueue.addBuffer(this.startBuffer);
            this.startBuffer = this.startBuffer.getNextBuffer();
        } else {
            Buffer temporal = this.startBuffer.getNextBuffer();
            Buffer temporal2 = this.startBuffer;
            boolean continuee = true;
            while (temporal.getNextBuffer() != null && continuee) {
                if (!temporal.isTaken()) {
                    temporal.setMessage(message);
                    bufferQueue.addBuffer(temporal);
                    temporal2.setNextBuffer(temporal.getNextBuffer());
                    continuee = false;
                } else {
                    temporal2 = temporal;
                    temporal = temporal.getNextBuffer();
                }
            }
            if (temporal.getNextBuffer() == null) {
                System.out.println("Message lost");
            }
        }
    }


    /**
     * Returns the size of the list
     * @return the size of the list
     */
    public int getSize(){
        Buffer temporal = this.startBuffer;
        int size = 1;
        while(temporal.getNextBuffer() != null){
            size++;
            temporal = temporal.getNextBuffer();
        }
        return size;
    }

    /**
     * Adds a node back to the list after the queue process a message.
     * @param newBuffer the new buffer to be added.
     */
    public void addNode(Buffer newBuffer){
        Buffer buffer = this.startBuffer;
        while(buffer.getNextBuffer() != null){
            buffer = buffer.getNextBuffer();
        } //aca ya estamos al final
        newBuffer.setMessage("");
        newBuffer.setTaken(false);
        buffer.setNextBuffer(newBuffer);
    }
}
