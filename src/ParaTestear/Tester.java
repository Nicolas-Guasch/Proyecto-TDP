package ParaTestear;

import Engine.Vector2;
import Engine.GameObject;
import Engine.IEngine;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import RenderingSystem.Window;

public class Tester
{
    public static void main(String[]a)
    {
        Window w = Window.GetInstance();
        IEngine eng = Engine.EngineFactory.Instance().get();
        ComportamientoTester c = new ComportamientoTester();
        Renderizable r = new Renderizable(new SpriteData(Paths.Alcon,new Vector2(100,100)));

        GameObject g = GameObject.getRoot().addChild();
        g.addComponent(r);
        g.addComponent(c);



        Renderizable death = new Renderizable(new SpriteData(Paths.DeathStar,new Vector2(500,500)),550);
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
