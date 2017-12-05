package ucr.ac.cr.ci1320.dataStructures;

/**
 * Created by josue on 26/11/17.
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

