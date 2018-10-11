package Observer;

import java.util.Collection;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Broadcaster<ParamType>
{

    private Collection<Listener<ParamType>> listeners;

    protected Broadcaster()
    {
        listeners = new ConcurrentLinkedQueue<>();
    }

    public final void unsuscribe(Listener<ParamType> listener)
    {
        listeners.remove(listener);
    }
    public final void suscribe(Listener<ParamType> listener)
    {
        listeners.add(listener);
    }
    protected final void broadcast(ParamType parameter)
    {
        listeners.forEach(e -> e.accept(parameter));
    }
}
