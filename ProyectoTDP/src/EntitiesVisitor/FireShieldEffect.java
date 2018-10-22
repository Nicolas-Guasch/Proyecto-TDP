package EntitiesVisitor;

import Entities.Bullets.FireShield;
import Entities.EnemyBullet;
import Entities.EntityData;
import Entities.Ships.EnemyShip;

public class FireShieldEffect extends VisitorEntity
{
    private final EntityData mydata;

    public FireShieldEffect(EntityData mine){
        mydata = mine;
    }

    @Override
    public void visit(EnemyShip enemyShip) {
        enemyShip.data().setHealth(-1);
        mydata.setHealth(-1);
    }

    @Override
    public void visit(EnemyBullet bullet) {
        bullet.data().setHealth(-1);
    }
}
