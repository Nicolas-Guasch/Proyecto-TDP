package Entities.Ships.EnemiesBuilders;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import ADTs.Vector2;
import Entities.Behaviours.FireFrequency;
import Entities.Behaviours.LookTarget;
import Entities.Builders.Concretes.TieBulletBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.EnemyBullet;
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

public class FastTieMaker extends EnemyShipBuilder
{


    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("commontie2");
        Renderizable rend = new Renderizable(data);
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
        director.setBuilder(new TieBulletBuilder(ship.getReferenced().getTransform()));
        EnemyShootFront esf = new EnemyShootFront(phaseshift,director,ship.getReferenced().getTransform());
        ship.addWeapon(esf);

        // --------------- configuro el arma para disparar cada 40 frames
        int freq = 30;
        WeaponSet bp = ship.getBagpack();
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
        handler = new Hybrid50Hunter(handler,PlayerShip.getInstance().getReferenced().getTransform());
        Pilot pilot = new Pilot(handler,ship,12f);
        ship.setPilot(pilot);
    }

    @Override
    public void assembleBehaviours() {
        // ---------------- hago que mire hacia abajo ----------
        ship.getReferenced().getTransform().setTop(Vector2.DOWN());
        // ---------------- hago que "pege la vuelta" -------------
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
        // ----------------- que alguna nave random mire al player ------------
        ship.addBehaviour(new LookTarget(PlayerShip.getInstance().getReferenced().getTransform()));
    }

    @Override
    public void assembleData() {
        ship.setData(GameSettings.GetInstance().TieData.clone());
    }
}