package Game.Units.Rewards;

import Game.Units.BasicUnit;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Kinematic.KinematicLine;
import Game.Units.Mods.Life.Life;
import Game.Units.Mods.Life.LifeNoCritic;
import Game.Units.Obstacles.OnCollideObstacleAll;
import Game.Units.OnCollide;

import java.awt.image.ImageConsumer;

public class SuperMisil extends Reward{

    public SuperMisil() {
        life=new LifeNoCritic();
        kinematic= new KinematicLine();
        data=new RewardData(10,10);
        onCollide=new OnCollideObstacleAll();
    }

    @Override
    public void accept(OnCollide c) {

    }




}
