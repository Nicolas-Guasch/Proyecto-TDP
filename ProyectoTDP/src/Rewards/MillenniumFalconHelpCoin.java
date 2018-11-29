package Rewards;

import Engine.Components.ITransform;
import Engine.GameObject;
import Engine.IGameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import EntitiesVisitor.SoloSupportVisitor;
import RenderingSystem.SpriteData;

import java.util.function.Consumer;

public class MillenniumFalconHelpCoin implements Consumer<ITransform> {



    @Override
    public void accept(ITransform originPoint) {

        IGameObject IGameObject = GameObject.getRoot().addChild();
        SpriteData spriteData = new SpriteData("soloreward");

        SoloSupportVisitor visitor = new SoloSupportVisitor();
        Entity reward = new GenericReward(IGameObject,visitor,spriteData);

        visitor.setEntity(reward);


        IGameObject.transform().setPosition(originPoint.position3());
        IGameObject.addComponent(new RewardMove());
        reward.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(reward);
    }
}
