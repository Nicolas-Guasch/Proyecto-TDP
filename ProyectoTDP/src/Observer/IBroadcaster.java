package Observer;

import java.util.function.Consumer;

public interface IBroadcaster<ParameterType>
{
    void unsuscribe(Consumer<ParameterType> listener);
    void suscribe(Consumer<ParameterType> listener);
    void clean();
}
