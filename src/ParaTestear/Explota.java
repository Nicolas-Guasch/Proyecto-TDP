package ParaTestear;

import Engine.Component;
import Engine.EngineFactory;
import Engine.GameObject;
import Engine.Vector2;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
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
            l.add(new VolatileComponent(12));
            l.add(new Renderizable(new SpriteData(Paths.NaveTester,new Vector2(20,20))));
            GameObject g = gameObject().addChild(l);
            v = v.rotate(0.05f);
        }
    }
}
