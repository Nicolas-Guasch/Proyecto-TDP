package ParaTestear;

import Engine.Component;
import Engine.Components.CollisionData;
import Engine.EngineFactory;

public class DeathIfTouchSomething extends Component
{


    @Override
    public void OnCollisionEnter(CollisionData data) {

        {
            data.Mine().setActive(false);
            if(gameObject()!=null)
                gameObject().sendMessage(s->s.setActive(false));

            EngineFactory.Instance().get().WaitForFrames(()->{


                if(gameObject()!=null)
                    gameObject().Destroy();
            },0);
        }
    }
}
