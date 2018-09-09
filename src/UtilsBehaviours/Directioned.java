package UtilsBehaviours;

import Engine.Component;
import Engine.Vector2;

public class Directioned extends Component
{
    private Vector2 Direction;
    public Directioned(Vector2 direction)
    {
        Direction = direction;
    }
    @Override
    public void Update()
    {
        if(gameObject()!=null)
            transform().MoveTowards(Direction);
    }
}
