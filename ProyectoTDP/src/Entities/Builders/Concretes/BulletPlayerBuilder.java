package Entities.Builders.Concretes;

import Engine.Components.CircleCollider;
import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Behaviours.SimpleBullet;
import Entities.Builders.PlayerBulletBuilder;
import GameData.GameSettings;
import GameData.SoundManager;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Stuff.Paths;


/**
 * Concrete Builder for Player Bullets
 */
public class BulletPlayerBuilder extends PlayerBulletBuilder
{
    private static final SpriteData SPRITEDATA = new SpriteData(Paths.LaserBlue,new Vector2(15,60));

    private final Transform solo;

    public BulletPlayerBuilder(Transform solo)
    {
        this.solo = solo;
    }

    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(SPRITEDATA);
        bullet.getReferenced().setRenderer(rend);
        rend.Show();
        SoundManager.Instance().SoloShoot();
    }

    @Override
    public void assembleCollider()
    {
        CircleCollider collider = new CircleCollider(SPRITEDATA,bullet);
        bullet.setCollider(collider);
    }

    @Override
    public void assembleBehaviours() {
        Transform tr = bullet.getReferenced().getTransform();
        tr.setPosition(solo.position());
        tr.setTop(solo.top());
        bullet.addBehaviour(new SimpleBullet(GameSettings.GetInstance().SoloBulletSpeed));
        tr.setZcomponent(-10);
    }

    @Override
    public void assembleData() {
        bullet.setData(GameSettings.GetInstance().SoloBulletData.clone());
    }
}
