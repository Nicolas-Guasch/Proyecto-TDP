package ParaTestear;

import Engine.Vector2;
import Engine.GameObject;
import Engine.IEngine;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import RenderingSystem.Window;
import UtilsBehaviours.MouseFollower;

public class Tester
{
    public static void main(String[]a)
    {
        System.out.println(Paths.Background);
        Window w = Window.GetInstance();
        IEngine eng = Engine.EngineFactory.Instance().get();
        ComportamientoTester c = new ComportamientoTester();
        MouseFollower mf = new MouseFollower();
        Renderizable r = new Renderizable(new SpriteData(Paths.Alcon,new Vector2(100,100)));

        GameObject g = GameObject.getRoot().addChild();
        g.addComponent(r);
        g.addComponent(c);
        g.addComponent(mf);

        g.getTransform().setTop(new Vector2(2,1));

        Renderizable death = new Renderizable(new SpriteData(Paths.DeathStar,new Vector2(500,500)));
        GameObject ds = GameObject.getRoot().addChild();
        ds.addComponent(death);
        death.Show();
        ds.addComponent(new AlwaysLateral(new Vector2(1,0.05f)));
        ds.getTransform().setPosition(new Vector2(-500,400));


        //TODO : Acordarme de darle SHOW a los Renderizables
        eng.SuscribeToUpdate(GameObject.getRoot());
        eng.SuscribeToUpdate(w);

        ((Window) w).Show();
        r.Show();
        eng.Start();

    }
}
