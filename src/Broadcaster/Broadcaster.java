package Broadcaster;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Broadcaster<P>
{
    private List<Consumer<P>> list;
    Broadcaster()
    {
        list = new LinkedList<>();
    }
    void Invoke(P p)
    {
        list.forEach((c)-> c.accept(p));
    }
    public void Suscribe(Consumer<P> listener)
    {
        list.add(listener);
    }
    public void Unsuscribe(Consumer<P> listener)
    {
        list.remove(listener);
    }
}
