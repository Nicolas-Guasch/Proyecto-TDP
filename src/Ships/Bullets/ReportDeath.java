package Ships.Bullets;

import Engine.Components.CollisionData;
import Engine.EngineFactory;

import Broadcaster.*;

public class ReportDeath extends BulletBehaviour<ReportDeath>
{
    private int frameDuration;
    private Invoker<Boolean> invokereport;
    private Broadcaster<Boolean> onDeath;



    public ReportDeath(int lifeFrames )
    {
        frameDuration = lifeFrames;
        BroadcasterPackage<Boolean> pack = BroadcasterFactory.GetBroadcaster();
        invokereport = pack.Invoker;
        onDeath = pack.Broadcaster;
    }

    @Override
    public ReportDeath clone() {
        return new ReportDeath(frameDuration);
    }

    @Override
    public void OnCollisionEnter(CollisionData data)
    {
        if(data.Mine().gameObject() == gameObject())
        {
            //invokereport.Invoke(true);
        }
    }

    public void OnEnable()
    {
        EngineFactory.Instance().get().WaitForFrames(()->{
            invokereport.Invoke(true);
        },frameDuration);
    }

    public Broadcaster<Boolean> OnDeath() {
        return onDeath;
    }
}
