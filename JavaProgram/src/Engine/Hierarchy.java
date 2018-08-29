package Engine;

import java.util.LinkedList;
import java.util.List;

public class Hierarchy
{
    private static Hierarchy hierarchy = null;
    public static Hierarchy CurrentHierarchy()
    {
        hierarchy = hierarchy == null ? new Hierarchy() : hierarchy;
        return hierarchy;
    }

    private Hierarchy(){}//because singleton

    public Iterable<GameObject> FirstLine()
    {
        return GameObject.GetRoot().children();
    }

    public GameObject AddChildInFirstLine()
    {
        return GameObject.GetRoot().AddLastChild();
    }

}
