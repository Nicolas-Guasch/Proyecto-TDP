package EntitiesVisitor;

import Entities.Ships.PlayerShip;
import EntitiesVisitor.VisitorEntity;
import Rewards.Entity;

public class VisitorHealPotion extends VisitorEntity {

    private Entity reward;
    private float potion=100;

    public VisitorHealPotion(){

    }

    public void setReward(Entity reward){
        this.reward = reward;
    }

    @Override
    public void visit(PlayerShip playerShip){
        var health = playerShip.data().getHealth();
        playerShip.data().setHealth(health+potion);
        System.out.println("Salud anterior="+ health);
        System.out.println("Salud final="+ playerShip.data().getHealth());

        reward.Destroy();
        reward.referenced().getHitbox().setActive(false);

    }
}
