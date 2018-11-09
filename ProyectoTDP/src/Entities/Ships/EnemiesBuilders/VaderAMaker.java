package Entities.Ships.EnemiesBuilders;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import ADTs.Vector2;
import AIs.FireFrequency;
import AIs.LookTarget;
import Engine.GameObject;
import Entities.Builders.Concretes.LaserSaber;
import Entities.Builders.Concretes.BulletMaker;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.EnemyBullet;
import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.Player.PlayerShip;
import Entities.Ships.ShipBoss;
import Entities.Weapons.ShotFront;
import Entities.Weapons.Arsenal;
import GameData.GameSettings;
import IAs.*;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import UtilsBehaviours.MirrorBounds;

public class VaderAMaker extends EnemyShipBuilder
{


    public void create()
    {
        var go = GameObject.getRoot().addChild();
        ship = new ShipBoss(go);
    }

    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("vadership_a");
        Renderizable rend = new Renderizable(data);
        rend.show();
        ship.setRenderer(rend);
    }

    @Override
    public void assembleHitBox() {
        //deberia ser 100 x 100 pero asi se dificulta mas
        HitBox hb = HitBox.getOne(90,90,ship);
        HitBoxesManager.getInstance().addHitBox(hb, HitBoxesManager.ENEMIES);
        ship.setHitBox(hb);
    }

    @Override
    public void assembleWeapons() {
        int phaseshift = 20;

        // -------------- creo armas y las agrego ---------------
        BulletDirector<EnemyBullet, EnemyBulletBuilder> vaddir = new BulletDirector<>();
        vaddir.setBuilder(new LaserSaber(ship.referenced().transform(),PlayerShip.getInstance().referenced().transform()));
        ShotFront esf = new ShotFront(phaseshift,vaddir,ship.referenced().transform());
        ship.addWeapon(esf);


        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletMaker(ship.referenced().transform()));
        ShotFront esf1 = new ShotFront(phaseshift,director,ship.referenced().transform());
        ship.addWeapon(esf1);
        ship.addWeapon(esf1);
        ship.addWeapon(esf1);
        ship.addWeapon(esf1);
        ship.addWeapon(esf1);


        // --------------- configuro el arma para disparar cada 30 frames
        //int freq = 20;//TODO: volver a 20
        int freq = 10;
        Arsenal bp = ship.getBagPack();
        FireFrequency fireFrequency = new FireFrequency(freq,bp); // hace qeu dispare cada freq frames
        ship.addBehaviour(fireFrequency);

        // --------------- configuro el piloto -----------------
        //GameSettings.GetInstance().getSpeed("whitetie"); //TODO: implementar esto
        EntityQuery handler = new DummyEntityQuery();
        handler = new RelativeLateral(handler,50);
        handler = new AbsoluteLateral(handler,100);
        handler = new Slippery(handler,200f, 0.3f); // ver como queda sino sacar

        Pilot pilot = new Pilot(handler,ship,8+GameSettings.difficulty);
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
        ship.setData(GameSettings.GetInstance().FirstBossData.clone());
        ship.data().setShield(0);
    }
}