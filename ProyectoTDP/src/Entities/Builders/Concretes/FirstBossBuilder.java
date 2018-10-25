package Entities.Builders.Concretes;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.Transform;
import ADTs.Vector2;
import AIs.FireFrequency;
import AIs.HorizontalMoveShip;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Ships.EnemyShipBuilder;
import Entities.EnemyBullet;
import Entities.Ships.PlayerShip;
import Entities.Weapons.GenericalWeapon;
import Entities.Weapons.LateralizedWeapon;
import GameData.GameSettings;

import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.HunterBullet;

public class FirstBossBuilder extends EnemyShipBuilder
{

    public final static SpriteData SPRITEDATA = new SpriteData("vadership",new Vector2(200,200));


    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(SPRITEDATA);
        ship.setRenderer(rend);
        rend.show();
    }

    @Override
    public void assembleHitBox()
    {
        HitBox rec = HitBox.getOne(new Vector2(130,130),ship);
        HitBoxesManager.getInstance().addHitBox(rec,HitBoxesManager.ENEMIES);
        ship.setHitBox(rec);
    }

    @Override
    public void assembleWeapons() {

    }

    @Override
    public void assembleBehaviours() {
        Transform target = PlayerShip.getInstance().referenced().transform();
        ship.addBehaviour(new HorizontalMoveShip(GameSettings.GetInstance().FirstBossSpeed,6));

        BulletDirector<EnemyBullet, EnemyBulletBuilder> director1 = new BulletDirector<>();
        director1.setBuilder(new LaserSaber(
                ship.referenced().transform(),
                target));

        BulletDirector<EnemyBullet, EnemyBulletBuilder> director2 = new BulletDirector<EnemyBullet, EnemyBulletBuilder>();
        director2.setBuilder(new BulletMaker(ship.referenced().transform()));

        BulletDirector<EnemyBullet, EnemyBulletBuilder> director3 = new BulletDirector<EnemyBullet, EnemyBulletBuilder>();
        director3.setBuilder(new BulletMaker(ship.referenced().transform()));


        int phaseshift = 22;


        var bp = ship.getBagPack();
        LateralizedWeapon<BulletDirector<EnemyBullet, EnemyBulletBuilder>> g = new LateralizedWeapon<>(ship.referenced().transform(),director1,4);
        g.setPhaseShift(35);
        bp.add(g);
        bp.add(new GenericalWeapon<>(ship.referenced().transform(),director2,2));
        bp.add(new GenericalWeapon<>(ship.referenced().transform(),director2,4));
        bp.add(new GenericalWeapon<>(ship.referenced().transform(),director2,6));
        bp.add(new GenericalWeapon<>(ship.referenced().transform(),director2,8));

        FireFrequency frqsh = new FireFrequency(5,bp);
        ship.addBehaviour(frqsh);
        ship.referenced().transform().setTop(Vector2.DOWN());
        ship.setData(GameSettings.GetInstance().FirstBossData.clone());
        ship.addBehaviour(new HunterBullet(target,0.1f));
    }

    @Override
    public void assembleData()
    {
        ship.setData(GameSettings.GetInstance().FirstBossData.clone());
    }
}
