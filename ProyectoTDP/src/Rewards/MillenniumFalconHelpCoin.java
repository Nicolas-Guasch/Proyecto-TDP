package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.PlayerShip;
import EntitiesVisitor.SoloSupportVisitor;
import RenderingSystem.SpriteData;

import java.util.function.Consumer;

public class MillenniumFalconHelpCoin implements Consumer<Transform> {



    @Override
    public void accept(Transform originPoint) {

        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("soloreward");

        var vis = new SoloSupportVisitor();
        Entity rew = new GenericReward(premio,vis,sd);

        vis.setEntity(rew);


        premio.transform().setPosition(originPoint.position3());
        premio.addComponent(new RewardMove());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }
}
