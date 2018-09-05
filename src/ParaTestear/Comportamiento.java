package ParaTestear;

import Engine.Component;

public class Comportamiento extends Component
{
    int i;
    public Comportamiento()
    {
        i=0;
    }

    public void Update()
    {
        System.out.println("Update: "+i);
        i++;
    }

    public void PhysicsUpdate(float delta)
    {

        System.out.println("On fixed, delta:" + delta + " ----<<>>"+delta);
    }

}
