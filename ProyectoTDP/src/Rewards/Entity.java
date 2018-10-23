package Rewards;

import Engine.GameObject;
import Entities.*;

public abstract class Entity extends Entities.Entity // TODO: desgeneralizar
{

    public Entity(GameObject referenced) {
        super(referenced);
    }

    public void Destroy()
    {
        data().setHealth(-1);
    }
}
