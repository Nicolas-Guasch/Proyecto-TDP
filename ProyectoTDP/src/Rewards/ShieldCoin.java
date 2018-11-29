package Rewards;

import Engine.Components.ITransform;
import Engine.GameObject;
import Engine.IGameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.ShieldVisitor;
import RenderingSystem.SpriteData;

public class ShieldCoin implements java.util.function.Consumer<ITransform> {
    @Override
    public void accept(ITransform ITransform) {
        IGameObject IGameObject = GameObject.getRoot().addChild();
        SpriteData spriteData = new SpriteData("rewardshield");

        ShieldVisitor visitor = new ShieldVisitor();
        Entity reward = new GenericReward(IGameObject,visitor,spriteData);
        visitor.setReward(reward);


        IGameObject.transform().setPosition(ITransform.position3());
        IGameObject.addComponent(new RewardMove());
        reward.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(reward);
    }
}
