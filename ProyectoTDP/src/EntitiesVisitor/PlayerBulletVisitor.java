package EntitiesVisitor;

import Entities.BarricadeBoth;
import Entities.BarricadeEnem;
import Entities.PlayerBullet;
import Entities.Ships.EnemyShip;

public class PlayerBulletVisitor extends VisitorEntity
{

    private final PlayerBullet bullet;

    public PlayerBulletVisitor(PlayerBullet bullet){
        this.bullet = bullet;
    }
    public void visit(EnemyShip ent)
    {
        ent.data().decHealth(bullet.data().getDamage());
        bullet.data().setHealth(0);
    }
    public void visit(BarricadeBoth ent) {
        ent.data().decHealth(bullet.data().getDamage());
        bullet.data().setHealth(0);
    }
    public void visit(BarricadeEnem ent) {
        ent.data().decHealth(bullet.data().getDamage());
        bullet.data().setHealth(0);
    }
}
