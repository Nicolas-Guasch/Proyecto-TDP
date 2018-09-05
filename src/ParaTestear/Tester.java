package ParaTestear;

import Engine.Component;
import Engine.IEngine;

public class Tester
{
    public static void main(String[]a)
    {
        Component c = new Comportamiento();
        IEngine eng = Engine.EngineFactory.Instance().get();
        eng.SuscribeToPhysicsUpdate(c);
        eng.SuscribeToUpdate(c);
        eng.Start();
    }
}
