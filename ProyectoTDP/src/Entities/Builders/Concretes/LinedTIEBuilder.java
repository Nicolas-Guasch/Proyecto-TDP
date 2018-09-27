package Entities.Builders.Concretes;

import Collisions.CircleCollider;
import Engine.Vector2;
import Entities.Weapons.EnemyShootFront;
import Entities.Behaviours.FireFrequency;
import Entities.Behaviours.HorizontalMoveShip;
import Entities.Behaviours.LookTarget;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Builders.EnemyShipBuilder;
import Entities.EnemyBullet;
import GameData.GameSettings;
import Levels.LevelOne;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Tools.Random;
import UtilsBehaviours.MirrorBounds;

public class LinedTIEBuilder extends EnemyShipBuilder
{

    public final static SpriteData SPRITEDATA = new SpriteData("whitetie",new Vector2(200,200));
    public final static SpriteData SPRITEDATA2 = new SpriteData("vadership",new Vector2(200,200));
    private SpriteData prefSpriteData;// = new SpriteData("commontie2",new Vector2(200,200));

    public LinedTIEBuilder()
    {
        prefSpriteData = Random.bool()?SPRITEDATA:SPRITEDATA2;
    }


    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(prefSpriteData);
        ship.setRenderer(rend);
        rend.Show();
    }

    @Override
    public void assembleCollider()
    {
        //CircleCollider rec = new CircleCollider(new Vector2(40,40),ship);
        Vector2 dim;
        if(prefSpriteData==SPRITEDATA)dim = new Vector2(40,40);
        else dim=new Vector2(130,130);
        CircleCollider rec = new CircleCollider(dim,ship);
        //RectangleCollider rec = new RectangleCollider(dim,ship);
        ship.setCollider(rec);
    }

    @Override
    public void assembleBehaviours() {
        ship.addBehaviour(new HorizontalMoveShip(GameSettings.GetInstance().TieSpeed,101));
        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new TieBulletBuilder(ship.getReferenced().getTransform()));
        int phaseshift = (prefSpriteData==SPRITEDATA2)?26:18;
        EnemyShootFront esf = new EnemyShootFront(phaseshift,director,ship.getReferenced().getTransform());
        var bp = ship.getBagpack();
        ship.addWeapon(esf);
        FireFrequency frqsh = new FireFrequency(40,bp);
        ship.addBehaviour(frqsh);
        ship.getReferenced().getTransform().setTop(Vector2.DOWN());
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
        if(Random.bool())
            ship.addBehaviour(new LookTarget(LevelOne.Instance().player.getReferenced().getTransform()));
    }

    @Override
    public void assembleData()
    {
        ship.setData(GameSettings.GetInstance().TieData.clone());
    }
}
