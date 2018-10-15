package Broadcaster;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

/**
 *  Models an observer
 * @param <ParameterType> Parameter of function to suscribe
 */
public final class Broadcaster<ParameterType> implements IBroadcaster<ParameterType>
{
    private List<Consumer<ParameterType>> list;
    private Queue<Consumer<ParameterType>> toAdd;
    private Queue<Consumer<ParameterType>> toRemove;

    Broadcaster()
    {
        list = new LinkedList<>();
        toAdd = new LinkedBlockingQueue<>();
        toRemove = new LinkedBlockingQueue<>();
    }

    void Invoke(ParameterType parameter)
    {
        while(!toAdd.isEmpty())
        {
            list.add(toAdd.remove());
        }
        while(!toRemove.isEmpty())
        {
            list.remove(toAdd.remove());
        }
        for (Consumer<ParameterType> c : list) {
            c.accept(parameter);
        }
    }

    public void Suscribe(Consumer<ParameterType> listener)
    {
        toAdd.add(listener);
    }
    public void Unsuscribe(Consumer<ParameterType> listener)
    {
        toRemove.add(listener);
    }
    public void Clean()
    {
        list.clear();
        toAdd.clear();
        toRemove.clear();
    }
}

