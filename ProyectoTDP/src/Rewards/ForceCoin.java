package Rewards;

import Engine.Components.ITransform;
import Engine.GameObject;
import Engine.IGameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import EntitiesVisitor.ForceVisitor;
import RenderingSystem.SpriteData;

public class ForceCoin implements java.util.function.Consumer<ITransform> {
    @Override
    public void accept(ITransform ITransform) {
        IGameObject premio = GameObject.getRoot().addChild();
        SpriteData sd = new SpriteData("rewardforce");

        ForceVisitor vis = new ForceVisitor();
        Entity rew = new GenericReward(premio,vis,sd);
        vis.setEntity(rew);


        premio.transform().setPosition(ITransform.position3());
        premio.addComponent(new RewardMove());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }
}
