package ParaTestear;

import Engine.Component;
import Engine.Components.CollisionData;
import Engine.EngineFactory;
import Engine.GameObject;

public class DeathIfTouchHazardous extends Component
{

    @Override
    public void OnCollisionEnter(CollisionData data) {
        if(data.Their().isHazardous())
        {
            data.Mine().setActive(false);

            EngineFactory.Instance().get().WaitForFrames(()->{
                if(gameObject()!=null)
                    gameObject().Destroy();
            },0);
        }
    }
}
