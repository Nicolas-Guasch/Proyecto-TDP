package Engine;

import Broadcaster.Broadcaster;
import Components.Collider;

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


    private List<Collider> colliders;
    private CollidersManager()
    {
        colliders = new LinkedList<>();
        Core.getInstance().OnFixedUpdate.Suscribe(this::FixedUpdate);
        GameObject.OnComponentCreated.Suscribe(this::ComponentWasCreated);
    }

    // TODO:   remover a los que son elimiados (oir al destroy)

    private void ComponentWasCreated(Component<Collider> c)
    {
        Collider col = c.gameObject().GetComponent(Collider.class);
        if(col != null)
        {
            colliders.add(col);
        }
    }

    private void FixedUpdate(float delta)
    {
        //TODO: HAY UN CUADRATICO POR FRAMEEEEE!!!, hay que desmanijear esto (pero primero testear)
        for (Collider c : colliders) {
            for (Collider d : colliders) {
                CollisionData data = d.CheckCollision(c);
                if (data != null) {
                    d.gameObject().reportCollision(data);
                    c.gameObject().reportCollision(data.invert());
                }
            }
        }
    }



}
