package Entities.Ships.EnemiesBuilders;

import ADTs.Vector2;
import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Entities.Entity;
import Entities.Ships.EnemyShip;
import Entities.Ships.EnemyShipBuilder;
import GameData.GameSettings;
import IAs.*;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.ShadowedRend;
import RenderingSystem.SpriteData;
import UtilsBehaviours.MirrorBounds;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.function.Predicate;

public class FullKamikazeeMaker extends EnemyShipBuilder {


    /*
    *
    * name : "CommonTie1"
        speed : 5
        weapons: "commonWeaponTie", 2
        collider: 96x96
    * */
    @Override
    public void assembleSprite() {
        Renderizable rend = new ShadowedRend(new SpriteData("commontie1"));
        rend.show();
        ship.setRenderer(rend);
    }

    @Override
    public void assembleHitBox() {
        HitBox hb = HitBox.getOne(96,96,ship);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.ENEMIES);
        ship.setHitBox(hb);
    }

    @Override
    public void assembleWeapons() {
        // no tiene armas

    }

    @Override
    public void assembleBehaviours() {
        var rand = Math.abs(new Random(hashCode()).nextInt(400))+400;
        EntityQuery handler = new EllipseMove(new DummyEntityQuery(),50,(rand/600f)*450);
        EntityQuery handler2 = new Kamikazee(new DummyEntityQuery());
        Predicate<Entity> toTest = new FalseNTimes(rand*rand/10000);
        var definitivehandler = new SwitchWhen(toTest,handler,handler2);
        Pilot pilot = new Pilot(definitivehandler,ship,(Math.abs(new Random().nextInt(3))+1)*3f);
        //GameSettings.GetInstance().TieSpeed);
        ship.setPilot(pilot);
        //usar en el que cambia Predicate<Entity> toTest = new MoreThanPercentHealth(GameSettings.GetInstance().TieData.getHealth(),50);


        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.05f),bottomLeft.prod(1.05f)));
    }

    @Override
    public void assembleData() {
        ship.setData(GameSettings.GetInstance().TieData.clone());
    }
}
