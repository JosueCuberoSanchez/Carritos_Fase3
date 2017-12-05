package ucr.ac.cr.ci1320.dataStructures;


/**
 * Created by josue on 26/11/17.
 */
public class BufferList {
    private Buffer startBuffer;

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

    public void printList(){
        Buffer temporal = this.startBuffer;
        while(temporal != null){
            System.out.println(temporal.getMessage());
            temporal = temporal.getNextBuffer();
        }
    }

    public Buffer addMessage(String message){
        Buffer buffer = this.startBuffer.setMessage(message);
        this.startBuffer = this.startBuffer.getNextBuffer();
        Buffer temporal = this.startBuffer;
        while(temporal.getNextBuffer() != null){
            temporal = temporal.getNextBuffer();
        }
        temporal.setNextBuffer(new Buffer(null));
        /*este while y esta asignacion son por que al sacar el primer nodo de la lista, esta quedaria con
         tamaño = size-1 elementos, entonces debemos agregar otro nodo vacio al final para que vuelva
         a quedar con tamaño = size*/
        return buffer;
    }

    public int getSize(){
        Buffer temporal = this.startBuffer;
        int size = 1;
        while(temporal.getNextBuffer() != null){
            size++;
            temporal = temporal.getNextBuffer();
        }
        return size;
    }
}
