package Entities.Builders.Concretes;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Behaviours.SimpleBullet;
import Entities.Builders.EnemyBulletBuilder;
import GameData.GameSettings;
import Audio.SoundManager;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

public class TieBulletBuilder extends EnemyBulletBuilder
{

    private static final SpriteData SPRITEDATA = new SpriteData("greenbullet",new Vector2(80,80));

    private final Transform tie;

    public TieBulletBuilder(Transform tie)
    {
        this.tie = tie;
    }

    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(SPRITEDATA);
        bullet.getReferenced().setRenderer(rend);
        rend.show();
        SoundManager.Instance().Pew(tie.position()); // plays tie shoot sound
    }

    @Override
    public void assembleHitBox()
    {
        var hitBox = HitBox.getOne(new Vector2(20,50),bullet);
        HitBoxesManager.getInstance().addHitBox(hitBox,HitBoxesManager.ENEMYBULLET);
        bullet.setHitBox(hitBox);
    }

    @Override
    public void assembleBehaviours() {
        Transform tr = bullet.getReferenced().getTransform();
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
