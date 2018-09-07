package ParaTestear;

import Engine.Component;
import Engine.Vector2;

public class AlwaysLateral extends Component
{

    private Vector2 vector;

    public AlwaysLateral(Vector2 vector)
    {
        this.vector = vector;
    }

    public void Update(){

        transform().MoveTowards(vector);

    }
}
