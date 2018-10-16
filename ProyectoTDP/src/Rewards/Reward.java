package Rewards;

import Engine.GameObject;
import Entities.*;

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
