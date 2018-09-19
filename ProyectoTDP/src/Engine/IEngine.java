package Engine;

import java.awt.event.KeyListener;
import java.util.function.Consumer;

public interface IEngine
{
    void SuscribeToUpdate(GameObject object);
    void SuscribeToPhysicsUpdate(GameObject object);
    void SuscribeToUpdate(Component component);
    void SuscribeToPhysicsUpdate(Component component);
    void SuscribeToUpdate(Consumer consumer);
    void SuscribeToPhysicsUpdate(Consumer<Float> consumer);

    void UnsuscribeFromUpdate(GameObject object);
    void UnsuscribeFromPhysicsUpdate(GameObject object);
    void UnsuscribeFromUpdate(Component component);
    void UnsuscribeFromPhysicsUpdate(Component component);
    void UnsuscribeFromUpdate(Consumer consumer);
    void UnsuscribeFromPhysicsUpdate(Consumer<Float> consumer);

    void WaitForFrames(Runnable action, int frames);
    void WaitForSeconds(Runnable action, float seconds);

    void Start();
    void Pause();
    void Continue();
    void Stop();
    boolean isPaused();


    CorePauser Pauser();
}
