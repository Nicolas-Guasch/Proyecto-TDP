package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
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

public class WeaponFiveCoin implements java.util.function.Consumer<Engine.Components.Transform> {
    @Override
    public void accept(Transform transform) {
        var player = PlayerShip.getInstance();
        GameObject gameObject = GameObject.getRoot().addChild();
        var spriteData = new SpriteData("5reward");
        WeaponReward reward = new WeaponReward(gameObject,spriteData);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(player.referenced().transform()));
        Weapon weapon = new AngularWeapon<>(player.referenced().transform(),director, 5);
        weapon.setName("penta");
        reward.setWeapon(weapon);
        gameObject.transform().setPosition(transform.position3());
        gameObject.addComponent(new RewardMove());
        reward.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(reward);
    }
}
