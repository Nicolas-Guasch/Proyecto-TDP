package EntitiesVisitor;

import Entities.Ships.EnemyShip;
import Entities.Ships.Player.PlayerShip;

import java.util.List;

public class WeaponSwitch extends VisitorEntity
{
    private boolean active;

    public WeaponSwitch(boolean active)
    {
        this.active=active;
    }

    @Override
    public void visit(PlayerShip playerShip) {
        playerShip.getBagPack().setActive(active);
    }
    @Override
    public void visit(EnemyShip enemyShip) {
        enemyShip.getBagPack().setActive(active);
    }
}
