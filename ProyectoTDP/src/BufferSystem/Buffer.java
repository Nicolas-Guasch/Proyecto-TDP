package BufferSystem;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Provides a way to make a buffer of a clonable type
 * @param <PrototypeType> Prototype Type
 */
public class Buffer<PrototypeType extends IClonable<PrototypeType>>
{

    private PrototypeType prototype;
    private int bufferSize;
    private int bufferMin;
    private Queue<PrototypeType> buffer;

    public Buffer(int bufferMin, int bufferSize, PrototypeType prototype)
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

    public void Recycle(PrototypeType p)
    {
        buffer.add(p);
    }

    public Collection<PrototypeType> clearBuffer()
    {
        Collection<PrototypeType> ret = new LinkedList<>(buffer);
        buffer.clear();
        return ret;
    }

    public Iterable<PrototypeType> getBuffereds()
    {
        check();
        return buffer;
    }

    public PrototypeType get()
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

    public PrototypeType getPrototype() {
        return prototype;
    }

    public void setPrototype(PrototypeType prototype) {
        this.prototype = prototype;
    }

    public int getBufferMin() {
        return bufferMin;
    }

    public void setBufferMin(int bufferMin) {
        this.bufferMin = bufferMin;
    }
}
