package BufferSystem;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer<Prot extends IClonable<Prot>>
{

    private Prot prototype;
    private int bufferSize;
    private int bufferMin;
    private Queue<Prot> buffer;

    public Buffer(int bufferMin, int bufferSize, Prot prototype)
    {
        buffer = new LinkedBlockingQueue<>();
        this.bufferSize = bufferSize;
        this.bufferMin = bufferMin;
        this.prototype = prototype;
        check();
    }

    private void check()
    {
        if(buffer.size()<bufferMin)
        {
            while(buffer.size()<bufferSize)
            {
                buffer.add(prototype.clone());
            }
        }
    }

    public void Recycle(Prot p)
    {
        buffer.add(p);
    }

    public Collection<Prot> clearBuffer()
    {
        Collection<Prot> ret = new LinkedList<>(buffer);
        buffer.clear();
        return ret;
    }

    public Iterable<Prot> getBuffereds()
    {
        check();
        return buffer;
    }

    public Prot get()
    {
        check();
        return buffer.remove();
    }


    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public Prot getPrototype() {
        return prototype;
    }

    public void setPrototype(Prot prototype) {
        this.prototype = prototype;
    }

    public int getBufferMin() {
        return bufferMin;
    }

    public void setBufferMin(int bufferMin) {
        this.bufferMin = bufferMin;
    }
}
