package ucr.ac.cr.ci1320.dataStructures;

/**
 * Created by josue on 26/11/17.
 */
public class BufferQueue {
    private Buffer startBuffer;

    public BufferQueue(){
        this.startBuffer = null;
    }

    public void printList(){
        Buffer temporal = this.startBuffer;
        while(temporal != null){
            System.out.println(temporal.getMessage());
            temporal = temporal.getNextBuffer();
        }
    }

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

    public String getNextBufferedMessage(){
        String message = null;
        if(this.startBuffer != null) {
            message = this.startBuffer.getMessage();
            this.startBuffer = this.startBuffer.getNextBuffer();
        } else {
            System.out.println("Returning null. No buffered messages found");
        }
        return message;
    }
}
