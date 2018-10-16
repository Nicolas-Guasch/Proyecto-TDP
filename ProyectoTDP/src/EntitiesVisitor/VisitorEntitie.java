package EntitiesVisitor;

import Entities.BarricadeEnem;
import Entities.BarricadeBoth;
import Entities.EnemyBullet;
import Entities.PlayerBullet;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;

public abstract  class VisitorEntitie
{
    public void visit(PlayerShip playerShip){}
    public void visit(EnemyShip enemyShip){}
    public void visit(BarricadeEnem barricade){}
    public void visit(BarricadeBoth barricade){}
    public void visit(PlayerBullet bullet){}
    public void visit(EnemyBullet bullet){}

}
