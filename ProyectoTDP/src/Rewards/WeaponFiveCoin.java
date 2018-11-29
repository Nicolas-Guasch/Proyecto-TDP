package Rewards;

import Engine.Components.ITransform;
import Engine.GameObject;
import Engine.IGameObject;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.PlayerBullet;
import Entities.Ships.Player.PlayerShip;
import Entities.Weapons.AngularWeapon;
import Entities.Weapons.Weapon;
import RenderingSystem.SpriteData;

public class WeaponFiveCoin implements java.util.function.Consumer<ITransform> {
    @Override
    public void accept(ITransform ITransform) {
        PlayerShip player = PlayerShip.getInstance();
        IGameObject referenced = GameObject.getRoot().addChild();
        SpriteData spriteData = new SpriteData("5reward");
        WeaponReward reward = new WeaponReward(referenced,spriteData);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<PlayerBullet, PlayerBulletBuilder>();
        director.setBuilder(new BulletPlayerBuilder(player.referenced().transform()));
        Weapon weapon = new AngularWeapon<BulletDirector<PlayerBullet, PlayerBulletBuilder>>(player.referenced().transform(),director, 5);
        weapon.setName("penta");
        reward.setWeapon(weapon);
        referenced.transform().setPosition(ITransform.position3());
        referenced.addComponent(new RewardMove());
        reward.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(reward);
    }
}
