package Entities.Builders.Concretes;

import Engine.Components.RectangleCollider;
import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Builders.EnemyBulletBuilder;
import GameData.GameSettings;
import GameData.SoundManager;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.DangerousHunter;
import Stuff.Paths;

public class BuilderBossBullets extends EnemyBulletBuilder
{
    private static final SpriteData SPRITEDATA = new SpriteData(Paths.LaserRed,new Vector2(25,70));
    private static final Vector2 TieWeaponPhaseshift = new Vector2(5,10);
    private final Transform tie;
    private final Transform target;

    public BuilderBossBullets(Transform tie,Transform target)
    {
        this.tie = tie;
        this.target = target;
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
