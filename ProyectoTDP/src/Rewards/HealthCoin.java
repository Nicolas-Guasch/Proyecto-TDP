package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.VisitorHealPotion;
import RenderingSystem.SpriteData;

public class HealthCoin implements java.util.function.Consumer<Engine.Components.Transform> {
    @Override
    public void accept(Transform transform) {
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("rewardhealth");

        var vis = new VisitorHealPotion();
        Entity rew = new GenericReward(premio,vis,sd);
        vis.setReward(rew);


        premio.transform().setPosition(transform.position3());
        premio.addComponent(new RewardMove());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }
}
