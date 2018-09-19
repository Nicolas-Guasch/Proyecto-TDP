package Entities.Builders.Concretes;

import Engine.Components.RectangleCollider;
import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Behaviours.SimpleBullet;
import Entities.Builders.EnemyBulletBuilder;
import GameData.GameSettings;
import GameData.SoundManager;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Stuff.Paths;

public class TieBulletBuilder extends EnemyBulletBuilder
{

    private static final SpriteData SPRITEDATA = new SpriteData(Paths.Laser,new Vector2(15,60));
    private static final Vector2 TieWeaponPhaseshift = new Vector2(5,10);
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
        rend.Show();
        SoundManager.Instance().Pew(tie.position()); // plays tie shoot sound
    }

    @Override
    public void assembleCollider()
    {
        RectangleCollider collider = new RectangleCollider(SPRITEDATA,bullet);
        bullet.setCollider(collider);
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
