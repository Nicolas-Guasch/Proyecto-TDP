package Rewards;

import Engine.Components.ITransform;
import Engine.GameObject;
import Engine.IGameObject;
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

public class IceWeaponCoin implements java.util.function.Consumer<ITransform> {
    @Override
    public void accept(ITransform originPoint) {
        PlayerShip player = PlayerShip.getInstance();
        IGameObject premio = GameObject.getRoot().addChild();
        SpriteData sd = new SpriteData("icereward");
        WeaponReward rew = new WeaponReward(premio,sd);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<PlayerBullet, PlayerBulletBuilder>();
        director.setBuilder(new ColdFireMaker());
        Weapon wea = new IceWeapon<BulletDirector<PlayerBullet, PlayerBulletBuilder>>(player.referenced().transform(),director,5);
        rew.setWeapon(wea);
        premio.transform().setPosition(originPoint.position3());
        premio.addComponent(new RewardMove());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }
}
