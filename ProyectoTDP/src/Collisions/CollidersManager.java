package Collisions;

import Engine.Component;
import Engine.EngineGetter;

import java.util.LinkedList;
import java.util.List;


//TODO : modelar distinto, poner las capas
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
        EngineGetter.Instance().get().SuscribeToUpdate(this);
    }

    public void Update()
    {
        Check();
    }
/*

    public void PhysicsUpdate(float deltaTime)
    {
        Check();
    }
*/
    private void Check() {
        colliders.forEach(c ->
        {
            colliders.stream().filter(d -> d.gameObject() != null && d.isActive() && c.gameObject() != null && c.isActive()).forEach(d ->
            {
                CollisionData data = d.CheckCollision(c);
                CollisionData data2 = c.CheckCollision(d);
                if (data != null && data2 != null && d != c) {
                    d.getEntity().reportCollision(data);
                    c.getEntity().reportCollision(data2);
                }
            });
        });
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
