package EntitiesVisitor;

import Entities.BarricadeBoth;
import Entities.BarricadeEnem;
import Entities.PlayerBullet;
import Entities.Ships.EnemyShip;
import Entities.Ships.ShipBoss;

public class PlayerBulletVisitor extends VisitorEntity
{

    private final PlayerBullet bullet;




    public PlayerBulletVisitor(PlayerBullet bullet){
        this.bullet = bullet;
    }

    public void visit(ShipBoss shipBoss) {
        shipBoss.data().takeDamage(bullet.data().getDamage());
        bullet.data().setHealth(0);
    }

    public void visit(EnemyShip ent)
    {
        ent.data().takeDamage(bullet.data().getDamage());
        bullet.data().setHealth(0);
    }
    public void visit(BarricadeBoth ent) {
        ent.data().takeDamage(bullet.data().getDamage());
        bullet.data().setHealth(0);
    }
    public void visit(BarricadeEnem ent) {
        ent.data().takeDamage(bullet.data().getDamage());
        bullet.data().setHealth(0);
    }
}
