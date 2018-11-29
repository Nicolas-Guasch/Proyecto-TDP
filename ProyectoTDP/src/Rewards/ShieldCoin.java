package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Engine.IGameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.ShieldVisitor;
import RenderingSystem.SpriteData;

public class ShieldCoin implements java.util.function.Consumer<Engine.Components.Transform> {
    @Override
    public void accept(Transform transform) {
        IGameObject IGameObject = GameObject.getRoot().addChild();
        SpriteData spriteData = new SpriteData("rewardshield");

        ShieldVisitor visitor = new ShieldVisitor();
        Entity reward = new GenericReward(IGameObject,visitor,spriteData);
        visitor.setReward(reward);


        IGameObject.transform().setPosition(transform.position3());
        IGameObject.addComponent(new RewardMove());
        reward.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(reward);
    }
}
