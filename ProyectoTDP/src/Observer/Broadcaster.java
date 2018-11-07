package Observer;

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

    void invoke(ParameterType parameter)
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
            if(c!=null)
            c.accept(parameter);
        }
    }

    public void suscribe(Consumer<ParameterType> listener)
    {
        toAdd.add(listener);
    }
    public void unsuscribe(Consumer<ParameterType> listener)
    {
        toRemove.add(listener);
    }
    public void clean()
    {
        list.clear();
        toAdd.clear();
        toRemove.clear();
    }
}

