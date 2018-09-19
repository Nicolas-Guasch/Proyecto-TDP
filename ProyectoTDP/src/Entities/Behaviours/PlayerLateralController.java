package Entities.Behaviours;

import Engine.Component;
import Engine.Vector2;
import InputManager.AbstractContinueInput;
import InputManager.ContinueKeyInput;


/**
 *  Implements an Easy way to move the player using A and D
 *  for move Left and Right using the speed indicated
 *  in the constructor parameter
 */
public class PlayerLateralController extends Component
{
    private float speed;
    private AbstractContinueInput left, right;

    public PlayerLateralController(float moveSpeed)
    {
        left = new ContinueKeyInput("aA");
        right = new ContinueKeyInput("dD");
        this.speed = moveSpeed;
    }

    @Override
    public void Update()
    {
        Vector2 move = Vector2.ORIGIN();
        if(left.happens())
        {
            move = Vector2.LEFT(speed);
        }
        if(right.happens())
        {
            move = move.sum(Vector2.RIGHT(speed));
        }
        transform().MoveTowards(move);
    }

    @Override
    public void OnDestroy()
    {
        left.Destroy();
        right.Destroy();
    }
}
