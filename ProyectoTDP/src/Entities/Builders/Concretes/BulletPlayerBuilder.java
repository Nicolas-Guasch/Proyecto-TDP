package Entities.Builders.Concretes;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.Transform;
import ADTs.Vector2;
import AIs.SimpleBullet;
import Entities.Builders.PlayerBulletBuilder;
import GameData.GameSettings;
import Audio.SoundManager;
import RenderingSystem.Renderizable;
import RenderingSystem.ShadowedRend;
import RenderingSystem.SpriteData;


/**
 * Concrete Builder for Player Bullets
 */
public class BulletPlayerBuilder extends PlayerBulletBuilder
{
    private static final SpriteData SPRITEDATA = new SpriteData("bluebullet",new Vector2(80,80));

    private final Transform solo;

    public BulletPlayerBuilder(Transform solo)
    {
        this.solo = solo;
    }

    @Override
    public void assembleSprite()
    {
        Renderizable rend = new ShadowedRend(SPRITEDATA);
        bullet.referenced().setRenderer(rend);
        rend.show();
        SoundManager.Instance().SoloShoot();
    }

    @Override
    public void assembleHitBox()
    {
        HitBox hb = HitBox.getOne(20,50,bullet);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.PLAYERBULLET);
        bullet.setHitBox(hb);
    }

    @Override
    public void assembleBehaviours() {
        Transform tr = bullet.referenced().transform();
        tr.setPosition(solo.position());
        tr.setTop(solo.top());
        bullet.addBehaviour(new SimpleBullet(GameSettings.GetInstance().PlayerBulletSpeed ));
        tr.setZcomponent(-10);
    }

    @Override
    public void assembleData()
    {
        bullet.setData(GameSettings.GetInstance().PlayerBulletData.clone());
    }
}
