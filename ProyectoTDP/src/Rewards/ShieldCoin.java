package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.PlayerShip;
import Entities.Ships.ShieldVisitor;
import RenderingSystem.SpriteData;

public class ShieldCoin implements java.util.function.Consumer<Engine.Components.Transform> {
    @Override
    public void accept(Transform transform) {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("rewardshield");

        var vis = new ShieldVisitor();
        Entity rew = new GenericReward(premio,vis,sd);
        vis.setReward(rew);


        premio.transform().setPosition(transform.position3());
        premio.addComponent(new RewardMove());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }
}
