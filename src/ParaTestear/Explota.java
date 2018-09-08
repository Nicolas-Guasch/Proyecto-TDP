package ParaTestear;

import Engine.Component;
import Engine.GameObject;
import Engine.Vector2;
import UtilsBehaviours.Directioned;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Explota extends Component
{

    @Override
    public void Start()
    {
        Vector2 v = Vector2.UP();
        List<Component> l;
        for(int i=0 ; i<10 ; i++)
        {
            l = new LinkedList<>();
            l.add(new Directioned(v));
            l.add(new VolatileComponent(2));
            GameObject g = gameObject().addChild(l);
            v = v.rotate(0.05f);
        }
    }
}
