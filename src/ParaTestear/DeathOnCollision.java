package ParaTestear;

import Engine.Component;
import Engine.Components.CollisionData;
import Engine.EngineFactory;

public class DeathOnCollision extends Component
{
    @Override
    public void OnCollisionEnter(CollisionData data) {
        if(data.Their() != data.Mine())
        {
            data.Mine().setActive(false);
            EngineFactory.Instance().get().WaitForFrames(()->{
                if(gameObject()!=null)
                    gameObject().Destroy();
            },1);
        }
    }
}
