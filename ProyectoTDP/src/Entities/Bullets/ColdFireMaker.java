package Entities.Bullets;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.Transform;
import Engine.EngineGetter;
import Engine.GameObject;
import AIs.SimpleBullet;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Ships.Player.PlayerShip;
import Entities.EveryOne;
import GameData.GameSettings;
import Audio.SoundManager;
import EntitiesVisitor.FreezeVisitor;

import RenderingSystem.Animation;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

public class ColdFireMaker extends PlayerBulletBuilder {




    @Override
    public void assembleSprite() {
        String name = "coldfire";
        SpriteData data = new SpriteData(name);
        Renderizable rend = new Renderizable(data);
        bullet.referenced().setRenderer(rend);
        rend.show();

        Animation anim = new Animation(name, rend);
        anim.setSpeed(5); // 60 es uno por frame, 0 es 1 por segundo
        bullet.addBehaviour(anim);

        SoundManager.Instance().SoloShoot();//why?
    }

    @Override
    public void assembleHitBox() {
        HitBox hb = HitBox.getOne(30,50,bullet);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.PLAYERBULLET);
        bullet.setHitBox(hb);
    }

    @Override
    public void assembleBehaviours() {
        Transform tr = bullet.referenced().transform();
        var playerT = PlayerShip.getInstance().referenced().transform();
        tr.setPosition(playerT.position3());
        tr.setTop(playerT.top());
        bullet.addBehaviour(new SimpleBullet(GameSettings.GetInstance().PlayerBulletSpeed *0.5f));
        // ----------- Ice Effect ------------
        bullet.setOnDeath(this::onColdFire);
    }

    @Override
    public void assembleData()
    {
        bullet.setData(GameSettings.GetInstance().PlayerBulletData.clone());
        bullet.data().setDamage(0);
        EveryOne.getInstance().add(bullet);
    }

    private void onColdFire()
    {
        GameObject g = GameObject.getRoot().addChild();
        Renderizable rend = new Renderizable(new SpriteData("coldfireexplo"));
        g.setRenderer(rend);
        rend.show();
        var t = bullet.referenced().transform();
        g.transform().setPosition(t.position3());
        EngineGetter.Instance().get().waitForFrames(g::destroy,10);
        EveryOne.getInstance().takeLazyVisitor(new FreezeVisitor(5, t, 200));
    }
}
