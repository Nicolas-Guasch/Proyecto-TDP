package UtilsBehaviours;

import Engine.Component;
import Engine.Vector2;

public class Directioned extends Component
{
    private Vector2 direction;
    public Directioned(Vector2 direction)
    {
        this.direction = direction;
    }
    @Override
    public void Update()
    {
        if(gameObject()!=null)
            transform().MoveTowards(direction);
    }
}
