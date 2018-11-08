package EntitiesVisitor;

import Entities.BarricadeBoth;
import Entities.BarricadeEnem;
import Entities.Entity;
import Entities.PlayerBullet;
import Entities.Ships.EnemyShip;
import Entities.Ships.ShipBoss;

public class PlayerBulletVisitor extends VisitorEntity
{

    private final Entity entity;




    public PlayerBulletVisitor(Entity bullet){
        this.entity = bullet;
    }

    public void visit(ShipBoss shipBoss) {
        shipBoss.data().takeDamage(entity.data().getDamage());
        entity.data().setHealth(0);
    }

    public void visit(EnemyShip ent)
    {
        ent.data().takeDamage(entity.data().getDamage());
        entity.data().setHealth(0);
    }
    public void visit(BarricadeBoth ent) {
        ent.data().takeDamage(entity.data().getDamage());

    }
    public void visit(BarricadeEnem ent) {
        ent.data().takeDamage(entity.data().getDamage());
        entity.data().setHealth(0);
    }
}
