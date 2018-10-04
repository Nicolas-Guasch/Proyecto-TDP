package Entities.Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.EntityData;
import Entities.PlayerBullet;
import Entities.Ships.PlayerShip;
import Entities.TheGrimReaper;
import Entities.Weapons.AngularWeapon;
import Entities.Weapons.Weapon;

public class RewardFactory
{

    public static void getWeaponReward(Transform originPoint)
    {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        WeaponReward rew = new WeaponReward(premio);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(player.getReferenced().getTransform()));
        Weapon wea = new AngularWeapon<>(player.getReferenced().getTransform(),director,5);
        rew.setWeapon(wea);
        premio.getTransform().setPosition(originPoint.position3());
        rew.setData(EntityData.WithEqualsValues(100));
        TheGrimReaper.Instance().add(rew);
    }


}
