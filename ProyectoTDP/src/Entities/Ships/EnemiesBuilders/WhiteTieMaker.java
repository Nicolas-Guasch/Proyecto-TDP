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
import Scripts.Directionable;
import UtilsBehaviours.MirrorBounds;

public class WhiteTieMaker extends EnemyShipBuilder
{


    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("whitetie");
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
        int freq = 40;
        Arsenal bp = ship.getBagPack();
        FireFrequency fireFrequency = new FireFrequency(freq,bp); // hace qeu dispare cada freq frames
        ship.addBehaviour(fireFrequency);
        ship.addBehaviour(new Directionable(Vector2.DOWN(0.1f)));

        // --------------- configuro el piloto -----------------
        //GameSettings.GetInstance().getSpeed("whitetie"); //TODO: implementar esto
        EntityQuery handler = new DummyEntityQuery();
        //handler = new RelativeLateral(handler,50);
        handler = new AbsoluteLateral(handler,100);
        handler = new Slippery(handler,200f, 0.3f); // ver como queda sino sacar
        Pilot pilot = new Pilot(handler,ship,15f);
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
        ship.addBehaviour(new MirrorBounds(topRight.prod(0.9f),bottomLeft.prod(0.8f)));
        // ----------------- que alguna nave random mire al player ------------
        ship.addBehaviour(new LookTarget(PlayerShip.getInstance().referenced().transform()));
    }

    @Override
    public void assembleData() {
        ship.setData(GameSettings.GetInstance().TieData.clone());
    }
}
