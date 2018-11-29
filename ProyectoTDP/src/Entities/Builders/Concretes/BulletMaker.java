package Entities.Builders.Concretes;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.ITransform;
import ADTs.Vector2;
import AIs.SimpleBullet;
import Entities.Builders.EnemyBulletBuilder;
import GameData.GameSettings;
import Audio.SoundManager;
import RenderingSystem.Renderizable;
import RenderingSystem.ShadowedRend;
import RenderingSystem.SpriteData;

public class BulletMaker extends EnemyBulletBuilder
{

    private static final SpriteData SPRITEDATA = new SpriteData("greenbullet",new Vector2(80,80));

    private final ITransform tie;

    public BulletMaker(ITransform tie)
    {
        this.tie = tie;
    }

    @Override
    public void assembleSprite()
    {
        Renderizable rend = new ShadowedRend(SPRITEDATA);
        bullet.referenced().setRenderer(rend);
        rend.show();
        SoundManager.Instance().Pew(tie.position()); // plays tie shoot sound
    }

    @Override
    public void assembleHitBox()
    {
        HitBox hitBox = HitBox.getOne(new Vector2(20,50),bullet);
        HitBoxesManager.getInstance().addHitBox(hitBox,HitBoxesManager.ENEMYBULLET);
        bullet.setHitBox(hitBox);
    }

    @Override
    public void assembleBehaviours() {
        ITransform tr = bullet.referenced().transform();
        tr.setPosition(tie.position());
        tr.setTop(tie.top());
        bullet.addBehaviour(new SimpleBullet(GameSettings.GetInstance().TieBulletSpeed));
        tr.setZcomponent(-10);
    }

    @Override
    public void assembleData() {
        bullet.setData(GameSettings.GetInstance().TieBulletData.clone());
    }
}
