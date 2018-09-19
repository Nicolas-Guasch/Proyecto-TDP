package Entities.Behaviours;

import Engine.Component;
import Engine.Vector2;
import InputManager.AbstractContinueInput;
import InputManager.ContinueKeyInput;

public class VerticalPlayer extends Component
{

    private float speed;
    private AbstractContinueInput front, back;
    public VerticalPlayer(float moveSpeed)
    {
        front = new ContinueKeyInput("Ww");
        back = new ContinueKeyInput("sS");
        this.speed = moveSpeed/4;
    }

    @Override
    public void Update()
    {
        Vector2 move = Vector2.ORIGIN();
        if(front.happens())
        {
            move = transform().top(speed);
        }
        if(back.happens())
        {
            move = move.sum(transform().top(-speed));
        }
        transform().MoveTowards(move);
    }

}
