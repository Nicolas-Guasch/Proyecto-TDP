package Entities.Builders.Concretes;

import Engine.Components.RectangleCollider;
import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Behaviours.FireFrequency;
import Entities.Behaviours.HorizontalMoveShip;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Builders.EnemyShipBuilder;
import Entities.EnemyBullet;
import Entities.Weapons.GenericalWeapon;
import Entities.Weapons.LateralizedWeapon;
import GameData.GameSettings;
import Levels.LevelOne;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.Hunter;
import Stuff.Paths;

public class FirstBossBuilder extends EnemyShipBuilder
{

    public final static SpriteData SPRITEDATA = new SpriteData(Paths.Vader,new Vector2(200,200));


    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(SPRITEDATA);
        ship.setRenderer(rend);
        rend.Show();
    }

    @Override
    public void assembleCollider()
    {
        RectangleCollider rec = new RectangleCollider(new Vector2(40,40),ship);
        ship.setCollider(rec);
    }

    @Override
    public void assembleBehaviours() {
        Transform target = LevelOne.Instance().player.getReferenced().getTransform();
        ship.addBehaviour(new HorizontalMoveShip(GameSettings.GetInstance().FirstBossSpeed,6));

        BulletDirector<EnemyBullet, EnemyBulletBuilder> director1 = new BulletDirector<>();
        director1.setBuilder(new BuilderBossBullets(
                ship.getReferenced().getTransform(),
                target));

        BulletDirector<EnemyBullet, EnemyBulletBuilder> director2 = new BulletDirector<EnemyBullet, EnemyBulletBuilder>();
        director2.setBuilder(new TieBulletBuilder(ship.getReferenced().getTransform()));

        BulletDirector<EnemyBullet, EnemyBulletBuilder> director3 = new BulletDirector<EnemyBullet, EnemyBulletBuilder>();
        director3.setBuilder(new TieBulletBuilder(ship.getReferenced().getTransform()));


        int phaseshift = 22;


        var bp = ship.getBagpack();
        LateralizedWeapon<BulletDirector<EnemyBullet, EnemyBulletBuilder>> g = new LateralizedWeapon<>(ship.getReferenced().getTransform(),director1,4);
        g.setPhaseShift(35);
        bp.add(g);
        bp.add(new GenericalWeapon<>(ship.getReferenced().getTransform(),director2,2));
        bp.add(new GenericalWeapon<>(ship.getReferenced().getTransform(),director2,4));
        bp.add(new GenericalWeapon<>(ship.getReferenced().getTransform(),director2,6));
        bp.add(new GenericalWeapon<>(ship.getReferenced().getTransform(),director2,8));

        FireFrequency frqsh = new FireFrequency(5,bp);
        ship.addBehaviour(frqsh);
        ship.getReferenced().getTransform().setTop(Vector2.DOWN());
        ship.setData(GameSettings.GetInstance().FirstBossData.clone());
        ship.addBehaviour(new Hunter(target,0.1f));
    }

    @Override
    public void assembleData()
    {
        ship.setData(GameSettings.GetInstance().FirstBossData.clone());
    }
}