package Entities.Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Bullets.ColdFireMaker;
import Entities.EntityData;
import Entities.PlayerBullet;
import Entities.Ships.PlayerShip;
import Entities.TheGrimReaper;
import Entities.Weapons.AngularWeapon;
import Entities.Weapons.IceWeapon;
import Entities.Weapons.Weapon;
import RenderingSystem.SpriteData;

public class RewardFactory
{

    public static void getWeaponReward(Transform originPoint)
    {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("5reward");
        WeaponReward rew = new WeaponReward(premio,sd);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(player.getReferenced().transform()));
        Weapon wea = new AngularWeapon<>(player.getReferenced().transform(),director,5);
        rew.setWeapon(wea);
        premio.transform().setPosition(originPoint.position3());
        rew.setData(EntityData.WithEqualsValues(100));
        TheGrimReaper.Instance().add(rew);
    }
    public static void getWeaponIceReward(Transform originPoint)
    {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("icereward");
        WeaponReward rew = new WeaponReward(premio,sd);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new ColdFireMaker());
        Weapon wea = new IceWeapon<>(player.getReferenced().transform(),director,5);
        rew.setWeapon(wea);
        premio.transform().setPosition(originPoint.position3());
        rew.setData(EntityData.WithEqualsValues(100));
        TheGrimReaper.Instance().add(rew);
    }


}
