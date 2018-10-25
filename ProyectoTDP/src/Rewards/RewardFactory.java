package Rewards;

import Engine.Components.Transform;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RewardFactory
{
    public static final RewardKey
            SHIELD = RewardKey.get(),
        FIVEWEAPON = RewardKey.get(),
        ICE = RewardKey.get(),
        HANSUPPORT = RewardKey.get(),
        FIRESHIELD = RewardKey.get(),
        FORCE = RewardKey.get(),
        HEALTH = RewardKey.get();


    private static RewardFactory instance;
    public static RewardFactory getInstance(){
        instance = instance==null?new RewardFactory():instance;
        return instance;
    }

    private Map<RewardKey, Consumer<Transform>> creators;

    private RewardFactory(){
        creators = new HashMap<>();
        creators.put(FIVEWEAPON,new WeaponFiveCoin());
        creators.put(ICE, new IceWeaponCoin());
        creators.put(SHIELD,new ShieldCoin());
        creators.put(FIRESHIELD,new FireSpinnerCoin());
        creators.put(HANSUPPORT,new MillenniumFalconHelpCoin());
        creators.put(HEALTH,new HealthCoin());
        creators.put(FORCE,new ForceCoin());
    }


    public void getReward(RewardKey key, Transform originPoint){
        var op = creators.getOrDefault(key,null);
        if(op!=null){
          op.accept(originPoint);
        }
    }



    







}
