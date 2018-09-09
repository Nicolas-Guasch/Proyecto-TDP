package ParaTestear;

import Engine.Component;
import Engine.Components.RectangleCollider;
import Engine.GameObject;
import Engine.Vector2;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

public class LaserMaker
{
    private static SpriteData Data = new SpriteData(Paths.Laser,new Vector2(15,60));
    private static SpriteData DataBlue = new SpriteData(Paths.LaserBlue,new Vector2(15,60));




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
        g.getTransform().setPosition(parent.getTransform().getPosition());
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
        g.getTransform().setPosition(parent.getTransform().getPosition());
        SoundManager.Instance().Pew();
        return g;
    }


    public static GameObject createBlue(GameObject parent, float speed)
    {
        GameObject g = parent.addChild();
        Component c1 = new VolatileComponent(300);
        Component c2 = new AlwaysLateral(g.getTransform().getTop().prod(speed));
        Renderizable rend = new Renderizable(DataBlue);
        g.addComponent(c1);
        g.addComponent(c2);
        g.addComponent(rend);
        g.addCollider(new RectangleCollider(new Vector2(DataBlue.getWidth(),DataBlue.getHeight()))).setHazardous(true);
        g.addComponent(new DeathOnCollision());
        rend.Show();
        g.getTransform().setPosition(parent.getTransform().getPosition());
        SoundManager.Instance().Pew();
        return g;
    }
}
