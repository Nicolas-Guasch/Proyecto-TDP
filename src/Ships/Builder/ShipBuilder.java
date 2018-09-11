package Ships.Builder;

import Ships.Entities.Ship;

public abstract class ShipBuilder
{

    protected Ship ship;

    public void createShip()
    {
        ship = new Ship();
    }

    public Ship getShip()
    {
        return ship;
    }

    public abstract void createRenderer();

    public abstract void createCollider();

    public abstract void createController();

    public abstract void createBulletLauncher();
}
