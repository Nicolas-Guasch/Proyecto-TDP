package ParaTestear;

import Engine.Component;
import Engine.Components.CollisionData;
import Engine.EngineFactory;
import Engine.GameObject;

public class ChangeIfTouchHazardous extends Component
{

    private Component tochange;

    public ChangeIfTouchHazardous(Component tochange)
    {
        this.tochange = tochange;
    }


    @Override
    public void OnCollisionEnter(CollisionData data) {
        if(data.Their().isHazardous())
        {
            data.Mine().setActive(false);

            EngineFactory.Instance().get().WaitForFrames(()->{
                setActive(false);
                gameObject().addComponent(tochange);
            },0);
        }
    }
}