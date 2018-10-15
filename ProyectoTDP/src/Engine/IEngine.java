package Engine;

import java.awt.event.KeyListener;
import java.util.function.Consumer;

public interface IEngine
{
    void suscribeToUpdate(GameObject object);
    void suscribeToPhysicsUpdate(GameObject object);
    void suscribeToUpdate(Component component);
    void suscribeToPhysicsUpdate(Component component);

    void unsuscribeFromUpdate(GameObject object);
    void unsuscribeFromPhysicsUpdate(GameObject object);
    void unsuscribeFromUpdate(Component component);
    void unsuscribeFromPhysicsUpdate(Component component);

    void waitForFrames(Runnable action, int frames);
    void waitForSeconds(Runnable action, float seconds);

    void Start();
    void Pause();
    void Continue();
    void Stop();
    boolean isPaused();

    CorePauser Pauser();
}
