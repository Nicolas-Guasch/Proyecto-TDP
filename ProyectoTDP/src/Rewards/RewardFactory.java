package Rewards;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Bullets.ColdFireMaker;
import Entities.EntityData;
import Entities.PlayerBullet;
import Entities.Ships.PlayerShip;
import Entities.Ships.ShieldVisitor;
import Entities.EveryOne;
import Entities.Weapons.AngularWeapon;
import Entities.Weapons.IceWeapon;
import Entities.Weapons.Weapon;
import RenderingSystem.SpriteData;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RewardFactory
{
    public static final RewardKey
        SHIELDREWARD = RewardKey.get(),
        WEAPON5REWARD = RewardKey.get(),
        WEAPONICEREWARD = RewardKey.get();


    private static RewardFactory instance;
    public static RewardFactory getInstance(){
        instance = instance==null?new RewardFactory():instance;
        return instance;
    }

    private Map<RewardKey, Consumer<Transform>> creators;

    private RewardFactory(){
        creators = new HashMap<>();
        creators.put(WEAPON5REWARD,this::getWeaponReward);
        creators.put(WEAPONICEREWARD,this::getWeaponIceReward);
        creators.put(SHIELDREWARD,this::getShieldReward);
    }

    public void getReward(RewardKey key, Transform originPoint){
        var op = creators.getOrDefault(key,null);
        if(op!=null){
          op.accept(originPoint);
        }
    }



    private void getShieldReward(Transform originPoint)
    {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("rewardshield");

        var vis = new ShieldVisitor();
        Reward rew = new GenericReward(premio,vis,sd);
        vis.setReward(rew);


        premio.transform().setPosition(originPoint.position3());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }



    private void getWeaponReward(Transform originPoint)
    {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("5reward");
        WeaponReward rew = new WeaponReward(premio,sd);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(player.referenced().transform()));
        Weapon wea = new AngularWeapon<>(player.referenced().transform(),director,5);
        rew.setWeapon(wea);
        premio.transform().setPosition(originPoint.position3());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }


    private void getWeaponIceReward(Transform originPoint)
    {
        var player = PlayerShip.getInstance();
        GameObject premio = GameObject.getRoot().addChild();
        var sd = new SpriteData("icereward");
        WeaponReward rew = new WeaponReward(premio,sd);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new ColdFireMaker());
        Weapon wea = new IceWeapon<>(player.referenced().transform(),director,5);
        rew.setWeapon(wea);
        premio.transform().setPosition(originPoint.position3());
        rew.setData(EntityData.WithEqualsValues(100));
        EveryOne.getInstance().add(rew);
    }


}
