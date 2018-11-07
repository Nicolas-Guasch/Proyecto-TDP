package Engine;

import java.awt.event.KeyListener;
import java.util.function.Consumer;

public interface IEngine
{
    void suscribeToUpdate(GameObject object);

    void suscribeToUpdate(Component component);





    void waitForFrames(Runnable action, int frames);
    void waitForSeconds(Runnable action, float seconds);

    void Start();
    void Pause();
    void Continue();
    void Stop();
    boolean isPaused();

    CorePauser Pauser();

    long frameCounter();
}
