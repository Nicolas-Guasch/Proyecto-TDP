package Entities.Ships.EnemiesBuilders;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import ADTs.Vector2;
import AIs.FireFrequency;
import AIs.LookTarget;
import Entities.Builders.Concretes.BulletMaker;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.EnemyBullet;
import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.Player.PlayerShip;
import Entities.Weapons.ShotFront;
import Entities.Weapons.Arsenal;
import GameData.GameSettings;
import IAs.*;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.ShadowedRend;
import RenderingSystem.SpriteData;
import UtilsBehaviours.MirrorBounds;

public class FastTieMaker extends EnemyShipBuilder
{


    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("commontie2");
        Renderizable rend = new ShadowedRend(data);
        rend.show();
        ship.setRenderer(rend);
    }

    @Override
    public void assembleHitBox() {
        HitBox hb = HitBox.getOne(100,100,ship);
        HitBoxesManager.getInstance().addHitBox(hb, HitBoxesManager.ENEMIES);
        ship.setHitBox(hb);
    }

    @Override
    public void assembleWeapons() {
        int phaseshift = 18;

        // -------------- creo el arma y la agrego ---------------
        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletMaker(ship.referenced().transform()));
        ShotFront esf = new ShotFront(phaseshift,director,ship.referenced().transform());
        ship.addWeapon(esf);

        // --------------- configuro el arma para disparar cada 40 frames
        int freq = 30;
        Arsenal bp = ship.getBagPack();
        FireFrequency fireFrequency = new FireFrequency(freq,bp); // hace qeu dispare cada freq frames
        ship.addBehaviour(fireFrequency);

        // --------------- configuro el piloto -----------------
        //GameSettings.GetInstance().getSpeed("whitetie"); //TODO: implementar esto
        EntityQuery handler = new DummyEntityQuery();
        //handler = new RelativeLateral(handler,50);
        var abso = new AbsoluteLateral(handler,40);
        abso.setRandomLevel(5);
        handler = abso;
        handler = new Slippery(handler,100f, 0.6f); // ver como queda sino sacar
        handler = new Hybrid50Hunter(handler);
        Pilot pilot = new Pilot(handler,ship,12f);
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
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.05f),bottomLeft.prod(1.05f)));
        // ----------------- que alguna nave random mire al player ------------
        ship.addBehaviour(new LookTarget(PlayerShip.getInstance().referenced().transform()));
    }

    @Override
    public void assembleData() {
        ship.setData(GameSettings.GetInstance().TieData.clone());
    }
}