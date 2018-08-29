package Components;

import Engine.GameObject;
import Engine.Hierarchy;
import Engine.MonoBehaviour;

public class CollidersManager extends MonoBehaviour
{
    private static CollidersManager instance;
    public static CollidersManager getInstance()
    {
        if(instance == null)
        {
            GameObject g = Hierarchy.CurrentHierarchy().AddChildInFirstLine();
            instance = g.AddComponent(CollidersManager.class);
        }
        return instance;
    }

    public void Update()
    {

    }
}
