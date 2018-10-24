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
        WEAPONICEREWARD = RewardKey.get(),
        SOLOSUPPORT = RewardKey.get(),
        FIRESHIELD = RewardKey.get(),
        HEALPOTION = RewardKey.get();


    private static RewardFactory instance;
    public static RewardFactory getInstance(){
        instance = instance==null?new RewardFactory():instance;
        return instance;
    }

    private Map<RewardKey, Consumer<Transform>> creators;

    private RewardFactory(){
        creators = new HashMap<>();
        creators.put(WEAPON5REWARD,new WeaponFiveCoin());
        creators.put(WEAPONICEREWARD, new IceWeaponCoin());
        creators.put(SHIELDREWARD,new ShieldCoin());
        creators.put(FIRESHIELD,new FireSpinnerCoin());
        creators.put(SOLOSUPPORT,new MillenniumFalconHelpCoin());
        creators.put(HEALPOTION,new HealthCoin());
    }


    public void getReward(RewardKey key, Transform originPoint){
        var op = creators.getOrDefault(key,null);
        if(op!=null){
          op.accept(originPoint);
        }
    }



    







}
