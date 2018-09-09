package Engine.Components;

import Engine.Component;
import Engine.EngineFactory;

import java.util.LinkedList;
import java.util.List;

public class CollidersManager extends Component
{
    private static CollidersManager instance;
    public static CollidersManager GetInstance(){
        if(instance == null)
        {
            instance = new CollidersManager();
        }
        return instance;
    }

    private List<AbstractCollider> colliders;
    private CollidersManager()
    {
        colliders = new LinkedList<>();
        EngineFactory.Instance().get().SuscribeToUpdate(this);
    }

    public void Update()
    {
        Check();
    }


    public void PhysicsUpdate(float deltaTime)
    {
        Check();
    }

    private void Check()
    {
        for (AbstractCollider c : colliders) {
            for (AbstractCollider d : colliders) {
                CollisionData data = d.CheckCollision(c);
                CollisionData data2 = c.CheckCollision(d);
                if (data != null && data2!=null && d!=c)
                {
                    if(d.gameObject()!=null && d.isActive()) {
                        d.gameObject().sendMessage((x) -> x.OnCollisionEnter(data));
                    }
                    if( c.gameObject()!=null && c.isActive())
                    {
                        c.gameObject().sendMessage((x)->x.OnCollisionEnter(data2));
                    }
                }
            }
        }
    }


    public void addCollider(AbstractCollider collider)
    {
        colliders.add(collider);
    }
    public void removeCollider(AbstractCollider collider)
    {
        colliders.remove(collider);
    }
}
