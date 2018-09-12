package ParaTestear;

import Engine.Component;
import Engine.Components.CollisionData;
import Engine.Components.Transform;
import Engine.EngineFactory;
import Engine.Vector2;

public class ComportamientoKamikazee extends Component
{

    private Transform target;
    private float speed;
    public ComportamientoKamikazee(Transform target, float speed)
    {
        this.speed = speed;
        this.target = target;

    }

    @Override
    public void Start() {
        EngineFactory.Instance().get().WaitForFrames(this::ActiveColl,10);
    }

    private void ActiveColl() // must check nulls, this could fail
    {
        if(gameObject()!=null && gameObject().getCollider()!=null)
            gameObject().getCollider().setActive(true);
    }


    @Override
    public void Update()
    {

        if(gameObject()!=null) // TODO: desmanijear
        {


            Vector2 v = target.position().minus(transform().position()).versor().prod(speed);
            v = v.sum(Vector2.Random(0.3f));
            transform().MoveTowards(v);
            transform().setTop(v);
        }
    }
    @Override
    public void OnCollisionEnter(CollisionData data) {
        if(data.Their().getEntity().hazard()>1)
        {
            data.Mine().setActive(false);

            EngineFactory.Instance().get().WaitForFrames(()->{
                if(gameObject()!=null)
                    gameObject().Destroy();
            },0);
        }
    }
}
