package Entities.Ships;

import ADTs.Vector2;
import Engine.EngineGetter;
import Entities.Entity;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.VisitorEntity;
import RenderingSystem.Renderizable;
import RenderingSystem.ShadowedRend;
import RenderingSystem.SpriteData;



public class ShieldVisitor extends VisitorEntity
{

    private Entity reward;
    float prevShield;
    PlayerShip visitable;

    private Renderizable rend_shield;
    private Renderizable rend_ship;

    public ShieldVisitor(){
        rend_shield = new ShadowedRend(new SpriteData("shield"));

    }

    public void setReward(Entity reward){
        this.reward = reward;
    }

    @Override
    public void visit(PlayerShip visitable){
        Vector2 point = visitable.referenced().transform().position();

        this.visitable = visitable;
        if(visitable.data()==null)return;
        prevShield = visitable.data().getShield();
        visitable.data().setShield(1); // invulnerable

        rend_ship = visitable.referenced().getRenderer();
        rend_shield.show();
        rend_ship.gameObject().setRenderer(rend_shield);

        Runnable restore = this::restore;

        reward.data().setHealth(-1);
        reward.referenced().getHitbox().setActive(false);




        visitable.referenced().transform().setPosition(point);

        EngineGetter.Instance().get().waitForFrames(restore,400);
    }

    private void restore()
    {
        visitable.data().setShield(prevShield);
        if(rend_shield.gameObject() == null) return;
        rend_shield.hide();
        if(rend_ship.gameObject() == null) return;
        rend_ship.gameObject().setRenderer(rend_ship);
    }

}
