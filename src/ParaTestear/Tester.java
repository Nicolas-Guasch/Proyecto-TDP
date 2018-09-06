package ParaTestear;

import Engine.Component;
import Engine.IEngine;
import ShowThings.Windor;

public class Tester
{
    public static void main(String[]a)
    {
        Component c = new Comportamiento();
        Component w = new Windor();
        IEngine eng = Engine.EngineFactory.Instance().get();
        //eng.SuscribeToPhysicsUpdate(c);
        eng.SuscribeToUpdate(w);
        ((Windor) w).Show();
        eng.Start();

    }
}
