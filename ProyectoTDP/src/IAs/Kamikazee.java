package IAs;

import ADTs.IVector2;
import ADTs.Vector2;
import Entities.Entity;
import Entities.Ships.Player.PlayerShip;

public class Kamikazee extends AIQueryDecorator
{

    public Kamikazee(EntityQuery decorated) {
        super(decorated);
        this.decorated = new WatchAnother(PlayerShip.getInstance().referenced().transform(), decorated);
    }

    @Override
    public IVector2 whereToMove(Entity ent)
    {
        return ent.referenced().transform().top();
    }

    @Override
    public IVector2 whereToSee(Entity ent)
    {
        return PlayerShip.getInstance().referenced().transform().position().sub(ent.referenced().transform().position()).withLength(3);
    }
}
