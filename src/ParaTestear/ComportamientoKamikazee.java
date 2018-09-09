package ParaTestear;

import Engine.Component;
import Engine.Components.Transform;
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
    public void Update()
    {
        if(gameObject()!=null) // TODO: desmanijear
        {
            Vector2 v = target.getPosition().minus(transform().getPosition()).versor().prod(speed);
            v = v.sum(Vector2.Random(0.3f));
            transform().MoveTowards(v);
            transform().setTop(v);
        }
    }
}
