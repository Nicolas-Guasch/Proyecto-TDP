package Entities.Rewards;

import Engine.Components.CollisionData;
import Engine.GameObject;
import Entities.*;

public abstract class Reward extends Entity
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