package Scripts;

import ADTs.IVector2;
import Engine.Component;
import ADTs.Vector2;


// lo usa la deathstar, no tocar!!
public class Directionable extends Component
{
    private IVector2 direction;
    public Directionable(IVector2 direction)
    {
        this.direction = direction;
    }

    @Override
    public void update() {
        transform().moveTowards(direction);
    }
}

