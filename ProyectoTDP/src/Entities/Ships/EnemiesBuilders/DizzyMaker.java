package Entities.Ships.EnemiesBuilders;

import ADTs.Vector2;
import Collisions.HitBox;
import Collisions.HitBoxesManager;
import AIs.FireFrequency;
import Entities.Builders.Concretes.BulletMaker;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.EnemyBullet;
import Entities.Ships.EnemyShipBuilder;
import Entities.Weapons.ShotFront;
import Entities.Weapons.Arsenal;
import GameData.GameSettings;
import IAs.*;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.ShadowedRend;
import RenderingSystem.SpriteData;
import java.util.*;
import UtilsBehaviours.MirrorBounds;

public class DizzyMaker extends EnemyShipBuilder
{
    @Override
    public void assembleSprite() {
        String filename = "commontie3";
        Renderizable rend = new ShadowedRend(new SpriteData(filename));
        rend.show();
        ship.setRenderer(rend);
    }

    @Override
    public void assembleHitBox() {
        HitBox hb = HitBox.getOne(70,80,ship);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.ENEMIES);
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
        int freq = 80;
        Arsenal bp = ship.getBagPack();
        FireFrequency fireFrequency = new FireFrequency(freq,bp); // hace qeu dispare cada freq frames
        ship.addBehaviour(fireFrequency);

        // ----------------------------------------
        EntityQuery handler = new DummyEntityQuery();
        handler = new EllipseMove(handler,(new Random().nextInt(6)/6f)*1.32f,3.2f*new Random().nextInt(3)/6f);
        handler = new Slippery(handler,200f, 0.3f);
        handler = new Spinner(handler);
        Pilot pilot = new Pilot(handler,ship,15f);
        ship.setPilot(pilot);
    }

    @Override
    public void assembleBehaviours() {
        // ---------------- hago que mire hacia abajo inicialmente ----------
        ship.referenced().transform().setTop(Vector2.DOWN());
        // ---------------- hago que "pege la vuelta" -------------
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1f),bottomLeft.prod(1f)));
    }

    @Override
    public void assembleData() {

    }
}
