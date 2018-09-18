package Scripts;

import Engine.Component;
import Engine.Components.Transform;
import Engine.Vector2;



public class Directionable extends Component
{
    private Vector2 direction;
    public Directionable(Vector2 direction)
    {
        this.direction = direction;
    }

    @Override
    public void Update() {
        transform().MoveTowards(direction);
    }
}

