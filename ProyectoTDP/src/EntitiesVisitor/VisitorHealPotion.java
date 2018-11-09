package EntitiesVisitor;

import Entities.Entity;
import Entities.Ships.Player.PlayerShip;


public class VisitorHealPotion extends VisitorEntity {

    private Entity reward;
    private float potion=500;

    public VisitorHealPotion(){

    }

    public void setReward(Entity reward){
        this.reward = reward;
    }

    @Override
    public void visit(PlayerShip playerShip){
        var health = playerShip.data().getHealth();
        playerShip.data().setHealth(health+potion);

        reward.data().setHealth(-1);
        //TODO: puede que deba agregarlo a everyone
        reward.referenced().getHitbox().setActive(false);

    }
}
