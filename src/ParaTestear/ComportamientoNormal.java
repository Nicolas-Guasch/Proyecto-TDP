package ParaTestear;

import Engine.Component;
import Engine.Vector2;


public class ComportamientoNormal extends Component
{
    private int i;
    private int max=200;
    private Vector2 CurrentDirection;
    public ComportamientoNormal()
    {
        i=0;
        CurrentDirection = Vector2.RIGHT();
    }

    public void Update()
    {
        if(i<=max)
            transform().MoveTowards(CurrentDirection);
        else
        {
            i=0;
            CurrentDirection = CurrentDirection.prod(-1);
        }
        i++;
    }

}
