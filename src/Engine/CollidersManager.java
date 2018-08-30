package Engine;

import Components.Collider;

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

    }


}
