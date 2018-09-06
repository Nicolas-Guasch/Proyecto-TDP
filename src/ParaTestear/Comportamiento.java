package ParaTestear;

import Engine.Component;

import java.util.Random;

public class Comportamiento extends Component
{
    Double G;
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
        System.out.printf("On fixed, delta: -- <> -- %.35f ----<<>>\n",delta);
        operacionQueTardaUnToque();
    }

    private void operacionQueTardaUnToque() {
        for(int LO = 0 ; LO <221233 ; LO++)
        {
            int x = 5;
            int y = 2;
            int z = 30+LO;
            Double d = 9993999.999912;
            for(int r=0 ; r<x*y ; r++)
            {
                G = d;
                Double HH = d*(d-x+i);
                d*=(23+G/z);
                d*=(y+i);
                G =d*290;
            }
            if(d>29003.12432*d)
            {
                d=-d;
                G=d+1;
            }
            G=G*-G+2*G*G;
        }
    }

}
