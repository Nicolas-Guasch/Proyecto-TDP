package ParaTestear;

import Engine.Components.Vector2;
import Engine.GameObject;
import Engine.IEngine;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import RenderingSystem.Window;

import javax.swing.*;

public class Tester
{
    public static void main(String[]a)
    {
        Window w = Window.GetInstance();

        ComportamientoTester c = new ComportamientoTester();
        Renderizable r = new Renderizable(new SpriteData(Paths.Alcon,new Vector2(100,100)));

        GameObject g = GameObject.getRoot().addChild();

        g.addComponent(r);
        g.addComponent(c);

        IEngine eng = Engine.EngineFactory.Instance().get();

        eng.SuscribeToUpdate(g);
        eng.SuscribeToUpdate(w);

        ((Window) w).Show();
        r.Show();
        eng.Start();

    }
}
