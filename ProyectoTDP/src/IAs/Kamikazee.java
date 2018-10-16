package IAs;

import ADTs.Vector2;
import Entities.Entity;
import Entities.Ships.PlayerShip;

public class Kamikazee extends AIQueryDecorator
{

    public Kamikazee(EntityQuery decorated) {
        super(decorated);
        this.decorated = new WatchAnother(PlayerShip.getInstance().getReferenced().transform(), decorated);

    }

    @Override
    public Vector2 whereToMove(Entity ent)
    {
        return ent.getReferenced().transform().top();
    }

    @Override
    public Vector2 whereToSee(Entity ent)
    {
        return decorated.whereToSee(ent);
    }
}
