package EntitiesVisitor;

import Entities.*;
import Entities.Bullets.FireShield;

import Entities.Ships.EnemyShip;
import Entities.Ships.Player.PlayerShip;
import Entities.Ships.ShipBoss;
import Rewards.GenericReward;
import Rewards.WeaponReward;

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
    public void visit(WeaponReward rew){}
    public void visit(GenericReward rew){}
    public void visit(ShipBoss shipBoss){}



}
