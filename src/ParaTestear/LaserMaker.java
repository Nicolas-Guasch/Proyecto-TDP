package ParaTestear;

import Engine.Component;
import Engine.GameObject;
import Engine.Vector2;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

public class LaserMaker
{
    private static SpriteData Data = new SpriteData(Paths.Laser,new Vector2(15,60));
    public static GameObject create(GameObject parent, float speed)
    {
        GameObject g = parent.addChild();
        Component c1 = new VolatileComponent(300);
        Component c2 = new AlwaysLateral(g.getTransform().getTop().prod(speed));
        Renderizable rend = new Renderizable(Data);
        g.addComponent(c1);
        g.addComponent(c2);
        g.addComponent(rend);
        rend.Show();
        g.getTransform().setPosition(parent.getTransform().getPosition());
        SoundManager.Instance().Pew();
        return g;
    }
}
