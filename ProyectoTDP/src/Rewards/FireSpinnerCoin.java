package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.VisitorShieldFireReward;
import RenderingSystem.SpriteData;

public class FireSpinnerCoin implements java.util.function.Consumer<Engine.Components.Transform> {

    @Override
    public void accept(Transform transform) {
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("rewardfire");

        var vis = new VisitorShieldFireReward();

        Entity rew = new GenericReward(premio,vis,sd);
        vis.setEntity(rew);

        premio.transform().setPosition(transform.position3());
        premio.addComponent(new RewardMove());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }
}
