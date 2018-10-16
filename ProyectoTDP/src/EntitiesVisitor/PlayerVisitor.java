package EntitiesVisitor;

import Entities.EnemyBullet;
import Entities.Ships.EnemyShip;

public class PlayerVisitor extends VisitorEntity
{
    @Override
    public void visit(EnemyShip ent) {
        ent.data().decHealth(ent.data().getDamage());
    }

    public void visit(EnemyBullet ent) {
        ent.data().decHealth(ent.data().getDamage());
    }
}
