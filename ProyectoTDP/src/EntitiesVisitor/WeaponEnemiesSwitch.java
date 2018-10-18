package EntitiesVisitor;

import Entities.Ships.EnemyShip;

public class WeaponEnemiesSwitch extends VisitorEntity
{
    private boolean active;

    public WeaponEnemiesSwitch(boolean active)
    {
        this.active=active;
    }

    @Override
    public void visit(EnemyShip enemyShip) {
        enemyShip.getBagPack().setActive(active);
    }
}
