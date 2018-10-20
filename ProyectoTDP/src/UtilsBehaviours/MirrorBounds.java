package UtilsBehaviours;

import Engine.Component;
import ADTs.Vector2;

public class MirrorBounds extends Component
{

    private Vector2 topRight, bottomLeft;

    private float x,y;

    public MirrorBounds(Vector2 topRight, Vector2 bottomLeft)
    {
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
    }

    @Override
    public void update()
    {
        x = transform().position().x();
        y = transform().position().y();

        if(x > topRight.x())
        {
            x = bottomLeft.x()+10;
        }
        if(x < bottomLeft.x())
        {
            x = topRight.x()-10;
        }

        if(y > topRight.y())
        {
            y = bottomLeft.y()+10;
        }
        if(y< bottomLeft.y())
        {
            y = topRight.y()-10;
        }

        transform().setPosition(new Vector2(x,y));

    }
}
