package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Bullets.ColdFireMaker;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.PlayerBullet;
import Entities.Ships.Player.PlayerShip;
import Entities.Weapons.IceWeapon;
import Entities.Weapons.Weapon;
import RenderingSystem.SpriteData;

public class IceWeaponCoin implements java.util.function.Consumer<Engine.Components.Transform> {
    @Override
    public void accept(Transform originPoint) {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("icereward");
        WeaponReward rew = new WeaponReward(premio,sd);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new ColdFireMaker());
        Weapon wea = new IceWeapon<>(player.referenced().transform(),director,5);
        rew.setWeapon(wea);
        premio.transform().setPosition(originPoint.position3());
        premio.addComponent(new RewardMove());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }
}
