package Entities.Bullets;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.Transform;
import Entities.Behaviours.SimpleBullet;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Ships.PlayerShip;
import Entities.TheGrimReaper;
import GameData.GameSettings;
import GameData.SoundManager;
import GenericVisitor.FreezeVisitor;
import Levels.EnemiesManager;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

public class ColdFireMaker extends PlayerBulletBuilder {




    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("coldfire");
        Renderizable rend = new Renderizable(data);
        bullet.getReferenced().setRenderer(rend);
        rend.show();
        SoundManager.Instance().SoloShoot();
    }

    @Override
    public void assembleHitBox() {
        HitBox hb = HitBox.getOne(40,90,bullet);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.PLAYERBULLET);
        bullet.setHitBox(hb);
    }

    @Override
    public void assembleBehaviours() {
        Transform tr = bullet.getReferenced().getTransform();
        var playerT = PlayerShip.getInstance().getReferenced().getTransform();
        tr.setPosition(playerT.position3());
        tr.setTop(playerT.top());
        bullet.addBehaviour(new SimpleBullet(GameSettings.GetInstance().SoloBulletSpeed*4));
        // ----------- Ice Effect ------------
        bullet.setDoOnDeath(this::onColdFire);
    }

    @Override
    public void assembleData()
    {
        bullet.setData(GameSettings.GetInstance().SoloBulletData.clone());
        bullet.getData().setDamage(0);
        TheGrimReaper.Instance().add(bullet);
    }

    private void onColdFire()
    {
        var t = bullet.getReferenced().getTransform();
        EnemiesManager.getInstance().computeOperation(new FreezeVisitor(5, t, 200));
    }
}
