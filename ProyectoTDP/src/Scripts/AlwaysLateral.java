package Scripts;

import Engine.Component;
import ADTs.Vector2;

public class AlwaysLateral extends Component
{

    protected Vector2 vector;

    public AlwaysLateral(Vector2 vector)
    {
        this.vector = vector;
    }

    public void Update()
    {
        transform().MoveTowards(vector);
        transform().setTop(vector);
    }
}
