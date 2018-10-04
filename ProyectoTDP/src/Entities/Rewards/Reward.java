package Entities.Rewards;

import Collisions.CollisionData;
import Engine.GameObject;
import Entities.*;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;

public abstract class Reward<RewardType extends Reward<RewardType>> extends Entity<RewardType> // TODO: desgeneralizar
{

    public Reward(GameObject referenced) {
        super(referenced);
    }


    public abstract void collideWith(PlayerShip ent) ;

    public abstract void collideWith(EnemyShip ent) ;

    public void collideWith(ObstacleBidirectional ent) {

    }

    protected void Destroy()
    {
        getData().setHealth(-1);
    }


    public void collideWith(ObstacleMonoDirectional ent) {

    }


    public void collideWith(PlayerBullet ent) {

    }


    public void collideWith(EnemyBullet ent) {

    }


    public void collideWith(Reward ent) {

    }

    @Override
    public void reportCollision(CollisionData data)
    {
        data.Their().collideWith(this);
    }
}
