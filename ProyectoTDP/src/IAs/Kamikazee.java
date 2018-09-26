package IAs;

import Engine.Vector2;
import Entities.Entity;
import Entities.PlayerShip;

public class Kamikazee extends AIQueryDecorator
{

    public Kamikazee(EntityQuery decorated)
    {
        super(decorated);
        this.decorated = new WatchAnother(PlayerShip.getInstance().getReferenced().getTransform(),decorated);
    }

    @Override
    public Vector2 whereToMove(Entity ent)
    {
        return ent.getReferenced().getTransform().top();
    }

    @Override
    public Vector2 whereToSee(Entity ent)
    {
        return decorated.whereToSee(ent);
    }
}
