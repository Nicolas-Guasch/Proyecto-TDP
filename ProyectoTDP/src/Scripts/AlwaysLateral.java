package Scripts;

import ADTs.IVector2;
import Engine.Component;
import ADTs.Vector2;

public class AlwaysLateral extends Component
{

    protected IVector2 vector;

    public AlwaysLateral(IVector2 vector)
    {
        this.vector = vector;
    }

    public void update()
    {
        transform().moveTowards(vector);
        transform().setTop(vector);
    }
}
