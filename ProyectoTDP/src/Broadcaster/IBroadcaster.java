package Broadcaster;

import java.util.function.Consumer;

public interface IBroadcaster<ParameterType>
{
    void Unsuscribe(Consumer<ParameterType> listener);
    void Suscribe(Consumer<ParameterType> listener);
    void Clean();
}
