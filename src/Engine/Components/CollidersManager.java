package Engine.Components;

import Engine.EngineFactory;

import java.util.LinkedList;
import java.util.List;

public class CollidersManager
{
    private static CollidersManager instance;
    public static CollidersManager GetInstance(){
        if(instance == null)
        {
            instance = new CollidersManager();
        }
        return instance;
    }

    private List<RectangleCollider> colliders;
    private CollidersManager()
    {
        colliders = new LinkedList<>();
        EngineFactory.Instance().get().SuscribeToPhysicsUpdate(this::FixedUpdate);
    }

    private void FixedUpdate(float delta)
    {
        //TODO: HAY UN CUADRATICO POR FRAMEEEEE!!!, hay que desmanijear esto (pero primero testear)
        for (RectangleCollider c : colliders) {
            for (RectangleCollider d : colliders) {
                CollisionData data = d.CheckCollision(c);
                if (data != null)
                {
                    d.gameObject().sendMessage((x)->x.OnCollisionEnter(data));
                    c.gameObject().sendMessage((x)->x.OnCollisionEnter(data.invert()));
                }
            }
        }
    }



}
