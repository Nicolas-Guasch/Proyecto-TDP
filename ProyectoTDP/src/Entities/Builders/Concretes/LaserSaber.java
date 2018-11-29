package Entities.Builders.Concretes;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.ITransform;
import ADTs.Vector2;
import Entities.Builders.EnemyBulletBuilder;
import GameData.GameSettings;
import Audio.SoundManager;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.DangerousHunter;



/**
 * Concrete EnemyBullet Builder for the Tester Boss
 *
 */
public class LaserSaber extends EnemyBulletBuilder
{
    private static final SpriteData SPRITEDATA = new SpriteData("redbullet",new Vector2(80,80));

    private final ITransform tie;
    private final ITransform target;

    public LaserSaber(ITransform tie, ITransform target)
    {
        this.tie = tie;
        this.target = target;
    }

    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(SPRITEDATA);
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
        bullet.addBehaviour(new DangerousHunter(
                target,
                GameSettings.GetInstance().TieBulletSpeed/12,
                1.03f,
                GameSettings.GetInstance().TieBulletSpeed*3
                ));
        tr.setZcomponent(-10);
    }

    @Override
    public void assembleData() {
        bullet.setData(GameSettings.GetInstance().TieBulletData.clone());
    }

}
