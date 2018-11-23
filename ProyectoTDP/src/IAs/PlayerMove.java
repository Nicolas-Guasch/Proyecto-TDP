package IAs;

import ADTs.IVector2;
import ADTs.Vector2;
import Entities.Entity;
import InputManager.AbstractDirectionalInput;

public class PlayerMove extends AIQueryDecorator
{
    private AbstractDirectionalInput Move;
    private AbstractDirectionalInput Top;


    public PlayerMove(EntityQuery eq,AbstractDirectionalInput move, AbstractDirectionalInput top)
    {
        super(eq);
        Move = move;
        Top = top;
    }

    @Override
    public IVector2 whereToMove(Entity ent)
    {
        return Move.Direction();
    }

    @Override
    public IVector2 whereToSee(Entity ent) {
        return Top.Direction();
    }
}
