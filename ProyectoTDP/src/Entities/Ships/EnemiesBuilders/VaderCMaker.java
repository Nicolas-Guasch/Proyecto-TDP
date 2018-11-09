package Entities.Ships.EnemiesBuilders;

import ADTs.Vector2;
import Collisions.HitBox;
import Collisions.HitBoxesManager;
import AIs.FireFrequency;
import AIs.LookTarget;
import Engine.GameObject;
import Entities.Builders.Concretes.BulletMaker;
import Entities.Builders.Concretes.LaserSaber;
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

public class VaderCMaker extends EnemyShipBuilder {




    public void create()
    {
        var go = GameObject.getRoot().addChild();
        ship = new ShipBoss(go);
    }

    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("vadership_c");
        Renderizable rend = new Renderizable(data);
        rend.show();
        ship.setRenderer(rend);
    }

    @Override
    public void assembleHitBox() {
        //deberia ser 100 x 100 pero asi se dificulta mas
        HitBox hb = HitBox.getOne(70, 70, ship);
        HitBoxesManager.getInstance().addHitBox(hb, HitBoxesManager.ENEMIES);
        ship.setHitBox(hb);
    }

    @Override
    public void assembleWeapons() {
        int phaseshift = 50; // si , 50

        // -------------- creo armas y las agrego ---------------


        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletMaker(ship.referenced().transform()));
        ShotFront tieArsenal = new ShotFront(phaseshift, director, ship.referenced().transform());
        ship.addWeapon(tieArsenal);

        BulletDirector<EnemyBullet, EnemyBulletBuilder> saberDirector = new BulletDirector<>();
        saberDirector.setBuilder(new LaserSaber(ship.referenced().transform(), PlayerShip.getInstance().referenced().transform()));
        ShotFront weaponLaserSaber = new ShotFront(phaseshift, saberDirector, ship.referenced().transform());
        ship.addWeapon(weaponLaserSaber);
        ship.addWeapon(weaponLaserSaber);
        ship.addWeapon(weaponLaserSaber);
        ship.addWeapon(weaponLaserSaber);


        // --------------- configuro el arma para disparar cada 30 frames
        int freq = 6-GameSettings.difficulty;
        Arsenal bp = ship.getBagPack();
        FireFrequency fireFrequency = new FireFrequency(freq, bp); // hace qeu dispare cada freq frames
        ship.addBehaviour(fireFrequency);

        // --------------- configuro el piloto -----------------
        //GameSettings.GetInstance().getSpeed("whitetie"); //TODO: implementar esto
        EntityQuery handler = new DummyEntityQuery();
        handler = new RelativeLateral(handler, 73);
        handler = new AbsoluteLateral(handler, 90);
        handler = new Slippery(handler, 190f, 0.31f); // ver como queda sino sacar

        Pilot pilot = new Pilot(handler, ship, 14*GameSettings.difficulty);
        float deg = 0.4f;
        handler = new IncreaseSpeedIfWatched(handler, pilot, deg);
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
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.3f), bottomLeft.prod(1.3f)));
        // ----------------- que alguna nave random mire al player ------------
        ship.addBehaviour(new LookTarget(PlayerShip.getInstance().referenced().transform()));
    }

    @Override
    public void assembleData() {
        ship.setData(GameSettings.GetInstance().FirstBossData.clone());
        ship.data().setShield(0.15f*GameSettings.difficulty);
    }
}
