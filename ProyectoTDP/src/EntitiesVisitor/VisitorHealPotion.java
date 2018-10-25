package EntitiesVisitor;

import Entities.Entity;
import Entities.Ships.PlayerShip;
import EntitiesVisitor.VisitorEntity;


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

        reward.data().setHealth(-1);
        //TODO: puede que deba agregarlo a everyone
        reward.referenced().getHitbox().setActive(false);

    }
}