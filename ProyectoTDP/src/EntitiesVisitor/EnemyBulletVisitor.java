package EntitiesVisitor;

import Entities.BarricadeBoth;
import Entities.EnemyBullet;
import Entities.Ships.PlayerShip;

public class EnemyBulletVisitor extends VisitorEntity {
    private final EnemyBullet bullet;

    public EnemyBulletVisitor(EnemyBullet enemyBullet) {
        this.bullet = enemyBullet;
    }

    @Override
    public void visit(BarricadeBoth barricade) {
        bullet.data().setHealth(-1);
    }

    @Override
    public void visit(PlayerShip playerShip) {
        playerShip.data().takeDamage(bullet.data().getDamage());
    }
}
