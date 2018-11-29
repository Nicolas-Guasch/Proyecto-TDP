package Rewards;

import Engine.Components.ITransform;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RewardsManager
{
    public static final RewardKey
            SHIELD = RewardKey.get(),
        FIVEWEAPON = RewardKey.get(),
        ICE = RewardKey.get(),
        HANSUPPORT = RewardKey.get(),
        FIRESHIELD = RewardKey.get(),
        FORCE = RewardKey.get(),
        HEALTH = RewardKey.get();


    private static RewardsManager instance;
    public static RewardsManager getInstance(){
        instance = instance==null?new RewardsManager():instance;
        return instance;
    }

    private Map<RewardKey, Consumer<ITransform>> creators;

    private RewardsManager(){
        creators = new HashMap<RewardKey, Consumer<ITransform>>();
        creators.put(FIVEWEAPON,new WeaponFiveCoin());
        creators.put(ICE, new IceWeaponCoin());
        creators.put(SHIELD,new ShieldCoin());
        creators.put(FIRESHIELD,new FireSpinnerCoin());
        creators.put(HANSUPPORT,new MillenniumFalconHelpCoin());
        creators.put(HEALTH,new HealthCoin());
        creators.put(FORCE,new ForceCoin());
    }


    public void getReward(RewardKey key, ITransform originPoint){
        Consumer<ITransform> op = creators.getOrDefault(key,null);
        if(op!=null){
          op.accept(originPoint);
        }
    }



    







}
