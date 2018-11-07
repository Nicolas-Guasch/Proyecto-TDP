package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.SoloSupportVisitor;
import RenderingSystem.SpriteData;

import java.util.function.Consumer;

public class MillenniumFalconHelpCoin implements Consumer<Transform> {



    @Override
    public void accept(Transform originPoint) {

        GameObject gameObject = GameObject.getRoot().addChild();
        var spriteData = new SpriteData("soloreward");

        var visitor = new SoloSupportVisitor();
        Entity reward = new GenericReward(gameObject,visitor,spriteData);

        visitor.setEntity(reward);


        gameObject.transform().setPosition(originPoint.position3());
        gameObject.addComponent(new RewardMove());
        reward.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(reward);
    }
}
