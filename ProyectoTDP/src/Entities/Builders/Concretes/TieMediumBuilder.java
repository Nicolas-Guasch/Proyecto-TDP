package Entities.Builders.Concretes;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Vector2;
import Entities.Behaviours.FireFrequency;
import Entities.Behaviours.HorizontalMoveShip;
import Entities.Behaviours.LookTarget;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Ships.EnemyShipBuilder;
import Entities.EnemyBullet;
import Entities.EntityData;
import Entities.Weapons.GenericalWeapon;
import GameData.GameSettings;

import Levels.LevelTester;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Tools.Random;
import UtilsBehaviours.MirrorBounds;

public class TieMediumBuilder extends EnemyShipBuilder
{

    public final static SpriteData SPRITEDATA = new SpriteData("commontie3",new Vector2(200,200));
    public final static SpriteData SPRITEDATA2 = new SpriteData("hybridtie",new Vector2(140,140));




    public TieMediumBuilder()
    {

    }
    @Override
    public void assembleSprite()
    {
        Renderizable rend;

        rend = Random.bool() ? new Renderizable(SPRITEDATA):new Renderizable(SPRITEDATA2);

        ship.setRenderer(rend);
        rend.show();
    }

    @Override
    public void assembleHitBox()
    {
        var rec = HitBox.getOne(new Vector2(130,130),ship);
        HitBoxesManager.getInstance().addHitBox(rec,HitBoxesManager.ENEMIES);
        ship.setHitBox(rec);
    }

    @Override
    public void assembleWeapons() {

    }

    @Override
    public void assembleBehaviours() {
        ship.addBehaviour(new HorizontalMoveShip(GameSettings.GetInstance().TieSpeed,101));
        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        var bullbuilder = new TieBulletBuilder(ship.getReferenced().getTransform());
        director.setBuilder(bullbuilder);

        var bp = ship.getBagpack();
        var weap = new GenericalWeapon<>(ship.getReferenced().getTransform(),director,3);
        weap.setPhaseShift(8);
        ship.addWeapon(weap);

        FireFrequency frqsh = new FireFrequency(30,bp);

        ship.addBehaviour(frqsh);

        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));


        if(Random.bool())
            ship.addBehaviour(new LookTarget(LevelTester.Instance().player.getReferenced().getTransform()));
        ship.getReferenced().getTransform().setTop(Vector2.DOWN());
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
