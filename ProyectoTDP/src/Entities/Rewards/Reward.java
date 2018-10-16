package Entities.Rewards;

import Collisions.CollisionData;
import Engine.GameObject;
import Entities.*;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;

public abstract class Reward extends Entity // TODO: desgeneralizar
{

    public Reward(GameObject referenced) {
        super(referenced);
    }

    public void Destroy()
    {
        data().setHealth(-1);
    }
}
