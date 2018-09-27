package Entities.Builders.Concretes;

import Engine.Components.CircleCollider;
import Engine.Components.RectangleCollider;
import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Behaviours.FireFrequency;
import Entities.Behaviours.HorizontalMoveShip;
import Entities.Behaviours.LookTarget;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Builders.EnemyShipBuilder;
import Entities.EnemyBullet;
import Entities.EntityData;
import Entities.Weapons.GenericalWeapon;
import Entities.Weapons.LateralizedWeapon;
import GameData.GameSettings;
import Levels.LevelOne;
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
        rend.Show();
    }

    @Override
    public void assembleCollider()
    {
        CircleCollider rec = new CircleCollider(new Vector2(40,40),ship);
        //RectangleCollider rec = new RectangleCollider(new Vector2(40,40),ship);
        ship.setCollider(rec);
    }

    @Override
    public void assembleBehaviours() {
        ship.addBehaviour(new HorizontalMoveShip(GameSettings.GetInstance().TieSpeed,101));
        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        BulletDirector<EnemyBullet, EnemyBulletBuilder> directorH = new BulletDirector<>();
        Transform target = LevelOne.Instance().player.getReferenced().getTransform();

        var bullbuilder = new TieBulletBuilder(ship.getReferenced().getTransform());
        directorH.setBuilder(new BuilderBossBullets(
                ship.getReferenced().getTransform(),
                target));
        director.setBuilder(bullbuilder);

        var bp = ship.getBagpack();
        var weap = new GenericalWeapon<>(ship.getReferenced().getTransform(),director,4);
        var weap1 = new GenericalWeapon<>(ship.getReferenced().getTransform(),director,4);
        var weap2 = new LateralizedWeapon<>(ship.getReferenced().getTransform(),directorH,2);
        weap.setPhaseShift(8);
        ship.addWeapon(weap);
        ship.addWeapon(weap1);
        ship.addWeapon(weap2);

        FireFrequency frqsh = new FireFrequency(10,bp);
        ship.addBehaviour(frqsh);
        ship.getReferenced().getTransform().setTop(Vector2.DOWN());
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
        ship.addBehaviour(new LookTarget(LevelOne.Instance().player.getReferenced().getTransform()));
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

