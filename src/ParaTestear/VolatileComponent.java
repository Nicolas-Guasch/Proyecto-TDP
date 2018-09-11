package ParaTestear;

import Engine.Component;
import Engine.EngineFactory;

public final class VolatileComponent extends Component
{

    private int frameDuration;
    public VolatileComponent(int lifeFrames)
    {
        frameDuration = lifeFrames;
    }

    @Override
    public void Start()
    {
        EngineFactory.Instance().get().WaitForFrames(this::run,frameDuration);
    }

    private void run() {
        if (gameObject() != null){
            gameObject().Destroy();
        }
    }
}
