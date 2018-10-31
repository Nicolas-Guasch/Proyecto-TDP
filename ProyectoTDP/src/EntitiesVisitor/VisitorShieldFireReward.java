package EntitiesVisitor;

import Engine.EngineGetter;
import Entities.Bullets.FireShieldMaker;
import Entities.Entity;
import Entities.EveryOne;
import Entities.PlayerBullet;
import Entities.Ships.Player.PlayerShip;

public class VisitorShieldFireReward extends VisitorEntity
{
    private Entity mine;
    private FireShieldMaker builder;
    private boolean enable = true;

    public VisitorShieldFireReward(){
        builder = new FireShieldMaker();
    }

    public void setEntity(Entity ent){
        mine = ent;
    }

    @Override
    public void visit(PlayerShip playerShip){
        if (!enable) return;
        builder.create();
        builder.assembleSprite();
        builder.assembleHitBox();
        builder.assembleBehaviours();
        builder.assembleData();
        PlayerBullet shield = builder.get();
        EveryOne.getInstance().add(shield);
        enable = false;
        mine.data().setHealth(-1);
        EngineGetter.Instance().get().waitForFrames(()->{
            if(shield.data() != null)
                shield.data().setHealth(-1);
        },60*5);

    }




}
