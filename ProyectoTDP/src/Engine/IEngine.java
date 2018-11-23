package Engine;

import java.awt.event.KeyListener;
import java.util.function.Consumer;

public interface IEngine
{
    void suscribeToUpdate(GameObject object);

    void suscribeToUpdate(Component component);





    void waitForFrames(Action action, int frames);

    void start();

    CorePauser Pauser();

    long frameCounter();
}
