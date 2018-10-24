package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.PlayerBullet;
import Entities.Ships.PlayerShip;
import Entities.Weapons.AngularWeapon;
import Entities.Weapons.Weapon;
import RenderingSystem.SpriteData;

public class WeaponFiveCoin implements java.util.function.Consumer<Engine.Components.Transform> {
    @Override
    public void accept(Transform transform) {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("5reward");
        WeaponReward rew = new WeaponReward(premio,sd);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(player.referenced().transform()));
        Weapon wea = new AngularWeapon<>(player.referenced().transform(),director,5);
        rew.setWeapon(wea);
        premio.transform().setPosition(transform.position3());
        premio.addComponent(new RewardMove());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }
}
