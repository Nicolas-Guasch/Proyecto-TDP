package Entities;

import Engine.Components.CollisionData;
import Engine.GameObject;

public class Reward extends Entity
{

    public Reward(GameObject referenced) {
        super(referenced);
    }


    public void collideWith(PlayerShip ent) {

    }


    public void collideWith(EnemyShip ent) {

    }


    public void collideWith(ObstacleBidirectional ent) {

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
