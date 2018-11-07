package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.Player.PlayerShip;
import Entities.Ships.ShieldVisitor;
import RenderingSystem.SpriteData;

public class ShieldCoin implements java.util.function.Consumer<Engine.Components.Transform> {
    @Override
    public void accept(Transform transform) {
        GameObject gameObject = GameObject.getRoot().addChild();
        var spriteData = new SpriteData("rewardshield");

        var visitor = new ShieldVisitor();
        Entity reward = new GenericReward(gameObject,visitor,spriteData);
        visitor.setReward(reward);


        gameObject.transform().setPosition(transform.position3());
        gameObject.addComponent(new RewardMove());
        reward.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(reward);
    }
}
