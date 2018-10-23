package Entities.Builders.Concretes;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.Transform;
import ADTs.Vector2;
import Entities.Behaviours.FireFrequency;
import Entities.Behaviours.HorizontalMoveShip;
import Entities.Behaviours.LookTarget;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Ships.EnemyShipBuilder;
import Entities.EnemyBullet;
import Entities.EntityData;
import Entities.Ships.PlayerShip;
import Entities.Weapons.GenericalWeapon;
import Entities.Weapons.LateralizedWeapon;
import GameData.GameSettings;

import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import UtilsBehaviours.MirrorBounds;

public class TieHardBuilder extends EnemyShipBuilder
{

    public final static SpriteData SPRITEDATA = new SpriteData("whitetie",new Vector2(200,200));




    public TieHardBuilder()
    {

    }
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
        var hb = HitBox.getOne(new Vector2(40,40),ship);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.ENEMIES);
        ship.setHitBox(hb);
    }

    @Override
    public void assembleWeapons() {

    }

    @Override
    public void assembleBehaviours() {
        ship.addBehaviour(new HorizontalMoveShip(GameSettings.GetInstance().TieSpeed,101));
        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        BulletDirector<EnemyBullet, EnemyBulletBuilder> directorH = new BulletDirector<>();
        Transform target = PlayerShip.getInstance().referenced().transform();

        var bullbuilder = new BulletMaker(ship.referenced().transform());
        directorH.setBuilder(new BuilderBossBullets(
                ship.referenced().transform(),
                target));
        director.setBuilder(bullbuilder);

        var bp = ship.getBagPack();
        var weap = new GenericalWeapon<>(ship.referenced().transform(),director,4);
        var weap1 = new GenericalWeapon<>(ship.referenced().transform(),director,4);
        var weap2 = new LateralizedWeapon<>(ship.referenced().transform(),directorH,2);
        weap.setPhaseShift(8);
        ship.addWeapon(weap);
        ship.addWeapon(weap1);
        ship.addWeapon(weap2);

        FireFrequency frqsh = new FireFrequency(10,bp);
        ship.addBehaviour(frqsh);
        ship.referenced().transform().setTop(Vector2.DOWN());
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
        ship.addBehaviour(new LookTarget(PlayerShip.getInstance().referenced().transform()));
    }

    @Override
    public void assembleData()
    {
        EntityData data = GameSettings.GetInstance().TieData.clone();
        data.setHealth(data.getHealth()*2);
        data.setDamage(data.getDamage()*2);
        ship.setData(data);
    }
}

