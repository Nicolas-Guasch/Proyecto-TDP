package Entities.Behaviours;

import Engine.Component;
import Engine.Vector2;
import InputManager.AbstractContinueInput;
import InputManager.ContinueKeyInput;

public class HorizontalPlayer extends Component
{

    private float speed;
    private AbstractContinueInput left, right;
    public HorizontalPlayer(float moveSpeed)
    {
        left = new ContinueKeyInput("Aa");
        right = new ContinueKeyInput("dD");
        this.speed = moveSpeed;
    }

    @Override
    public void Update()
    {
        Vector2 move = Vector2.ORIGIN();
        if(left.happens())
        {
            move = transform().top().right(-speed);
        }
        if(right.happens())
        {
            move = move.sum(transform().top().right(speed));
        }
        transform().MoveTowards(move);
    }

}
