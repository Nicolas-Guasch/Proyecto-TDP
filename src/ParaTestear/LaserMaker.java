package ParaTestear;

import Engine.Component;
import Engine.Components.RectangleCollider;
import Engine.GameObject;
import Engine.Vector2;
import Entities.Bullet;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Stuff.Paths;

public class LaserMaker
{
    private static SpriteData Data = new SpriteData(Paths.Laser,new Vector2(15,60));
    private static SpriteData DataBlue = new SpriteData(Paths.LaserBlue,new Vector2(15,60));


    public static GameObject create(GameObject parent, Vector2 lateral)
    {
        GameObject g = parent.addChild();
        Component c1 = new VolatileComponent(4220);
        Component c2 = new AlwaysLateral(lateral);
        Renderizable rend = new Renderizable(Data);
        g.addComponent(c1);
        g.addComponent(c2);
        g.addComponent(rend);
        rend.Show();
        g.getTransform().setPosition(parent.getTransform().position());
        SoundManager.Instance().Pew();
        return g;
    }

    public static GameObject create(GameObject parent, float speed)
    {
        GameObject g = parent.addChild();
        Component c1 = new VolatileComponent(20);
        Component c2 = new AlwaysLateral(g.getTransform().getTop().prod(speed));
        Renderizable rend = new Renderizable(Data);
        g.addComponent(c1);
        g.addComponent(c2);
        g.addComponent(rend);
        rend.Show();
        g.getTransform().setPosition(parent.getTransform().position());
        SoundManager.Instance().Pew();
        return g;
    }

    public static GameObject create(GameObject parent, float speed, float random)
    {
        GameObject g = parent.addChild();
        Component c1 = new VolatileComponent(120);
        Component c2 = new AlwaysLateral(g.getTransform().getTop(2).sum(Vector2.Random(0.5f)).prod(speed));
        Renderizable rend = new Renderizable(Data);
        g.addComponent(c1);
        g.addComponent(c2);
        g.addComponent(rend);
        rend.Show();
        g.getTransform().setPosition(parent.getTransform().position());
        SoundManager.Instance().Pew();
        return g;
    }



    public static GameObject laserSolo(GameObject parent, float speed)
    {
        GameObject g = parent.addChild();
        Bullet b = new Bullet(90,g);
        Component c1 = new VolatileComponent(2123);
        Component c2 = new AlwaysLateral(g.getTransform().getTop().prod(speed));
        Renderizable rend = new Renderizable(DataBlue);
        g.addComponent(c1);
        g.addComponent(c2);
        g.addComponent(rend);

        g.addCollider(new RectangleCollider(new Vector2(10,40),b));

        g.addComponent(new DeathIfTouchSomething());
        rend.Show();
        g.getTransform().setPosition(parent.getTransform().position().sum(parent.getTransform().getTop(80)));
        SoundManager.Instance().Pew();
        return g;
    }
}
