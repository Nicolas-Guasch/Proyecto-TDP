package EntitiesVisitor;

import Entities.*;
import Entities.Bullets.FireShield;
import Rewards.Entity;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;

public abstract  class VisitorEntity
{
    public void visit(PlayerShip playerShip){}
    public void visit(EnemyShip enemyShip){}
    public void visit(OtherShip enemyShip){}
    public void visit(BarricadeEnem barricade){}
    public void visit(BarricadeBoth barricade){}
    public void visit(PlayerBullet bullet){}
    public void visit(EnemyBullet bullet){}
    public void visit(FireShield fireshield){}

    public void visit(Entity rew){}

}
