package Entities.Ships.EnemiesBuilders;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import ADTs.Vector2;
import Entities.Behaviours.FireFrequency;
import Entities.Behaviours.LookTarget;
import Entities.Builders.Concretes.BuilderBossBullets;
import Entities.Builders.Concretes.TieBulletBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.EnemyBullet;
import Entities.EntityData;
import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.PlayerShip;
import Entities.Weapons.EnemyShootFront;
import Entities.Weapons.WeaponSet;
import GameData.GameSettings;
import IAs.*;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import UtilsBehaviours.MirrorBounds;

public class VaderTieMaker extends EnemyShipBuilder
{


    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("vadership");
        Renderizable rend = new Renderizable(data);
        rend.show();
        ship.setRenderer(rend);
    }

    @Override
    public void assembleHitBox() {
        //deberia ser 100 x 100 pero asi se dificulta mas
        HitBox hb = HitBox.getOne(80,80,ship);
        HitBoxesManager.getInstance().addHitBox(hb, HitBoxesManager.ENEMIES);
        ship.setHitBox(hb);
    }

    @Override
    public void assembleWeapons() {
        int phaseshift = 20;

        // -------------- creo armas y las agrego ---------------
        BulletDirector<EnemyBullet, EnemyBulletBuilder> vaddir = new BulletDirector<>();
        vaddir.setBuilder(new BuilderBossBullets(ship.referenced().transform(),PlayerShip.getInstance().referenced().transform()));
        EnemyShootFront esf = new EnemyShootFront(phaseshift,vaddir,ship.referenced().transform());
        ship.addWeapon(esf);


        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new TieBulletBuilder(ship.referenced().transform()));
        EnemyShootFront esf1 = new EnemyShootFront(phaseshift,director,ship.referenced().transform());
        ship.addWeapon(esf1);
        ship.addWeapon(esf1);
        ship.addWeapon(esf1);
        ship.addWeapon(esf1);
        ship.addWeapon(esf1);


        // --------------- configuro el arma para disparar cada 30 frames
        int freq = 20;
        WeaponSet bp = ship.getBagPack();
        FireFrequency fireFrequency = new FireFrequency(freq,bp); // hace qeu dispare cada freq frames
        ship.addBehaviour(fireFrequency);

        // --------------- configuro el piloto -----------------
        //GameSettings.GetInstance().getSpeed("whitetie"); //TODO: implementar esto
        EntityQuery handler = new DummyEntityQuery();
        handler = new RelativeLateral(handler,50);
        handler = new AbsoluteLateral(handler,100);
        handler = new Slippery(handler,200f, 0.3f); // ver como queda sino sacar

        Pilot pilot = new Pilot(handler,ship,15f);
        handler = new IncreaseSpeedIfWatched(handler,pilot);
        pilot.setHandler(handler);
        ship.setPilot(pilot);

    }

    @Override
    public void assembleBehaviours() {
        // ---------------- hago que mire hacia abajo ----------
        ship.referenced().transform().setTop(Vector2.DOWN());
        // ---------------- hago que "pege la vuelta" -------------
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
        // ----------------- que alguna nave random mire al player ------------
        ship.addBehaviour(new LookTarget(PlayerShip.getInstance().referenced().transform()));
    }

    @Override
    public void assembleData()
    {
        ship.setData(EntityData.WithEqualsValues(80));
        ship.data().setShield(0);
    }
}