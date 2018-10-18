package Entities.Bullets;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.Transform;
import Engine.EngineGetter;
import Engine.GameObject;
import Entities.Behaviours.SimpleBullet;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Ships.PlayerShip;
import Entities.EveryOne;
import GameData.GameSettings;
import Audio.SoundManager;
import GenericVisitor.FreezeVisitor;
import TesterLevels.EnemiesManager;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

public class ColdFireMaker extends PlayerBulletBuilder {




    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("coldfire");
        Renderizable rend = new Renderizable(data);
        bullet.referenced().setRenderer(rend);
        rend.show();
        SoundManager.Instance().SoloShoot();
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
        EngineGetter.Instance().get().waitForFrames(g::Destroy,10);
        EnemiesManager.getInstance().computeOperation(new FreezeVisitor(5, t, 200));
    }
}
