package EntitiesVisitor;

import Entities.EnemyBullet;
import Entities.Ships.PlayerShip;

public class EnemyBulletVisitor extends VisitorEntitie {
    private final EnemyBullet bullet;

    public EnemyBulletVisitor(EnemyBullet enemyBullet) {
        this.bullet = enemyBullet;
    }

    @Override
    public void visit(PlayerShip playerShip) {
        playerShip.data().decHealth(bullet.data().getDamage());
    }
}
