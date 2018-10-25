package Entities.Builders.Concretes;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import ADTs.Vector2;
import Entities.Ships.PlayerShip;
import Entities.Weapons.ShotFront;
import AIs.FireFrequency;
import AIs.HorizontalMoveShip;
import AIs.LookTarget;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Ships.EnemyShipBuilder;
import Entities.EnemyBullet;
import GameData.GameSettings;

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
        rend.show();
    }

    @Override
    public void assembleHitBox()
    {
        //CircleCollider rec = new CircleCollider(new Vector2(40,40),ship);
        Vector2 dim;
        if(prefSpriteData==SPRITEDATA)dim = new Vector2(40,40);
        else dim=new Vector2(130,130);

        var hitBox = HitBox.getOne(dim,ship);
        HitBoxesManager.getInstance().addHitBox(hitBox,HitBoxesManager.ENEMIES);
        ship.setHitBox(hitBox);

    }

    @Override
    public void assembleWeapons() {

    }

    @Override
    public void assembleBehaviours() {
        ship.addBehaviour(new HorizontalMoveShip(GameSettings.GetInstance().TieSpeed,101));
        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletMaker(ship.referenced().transform()));
        int phaseshift = (prefSpriteData==SPRITEDATA2)?26:18;
        ShotFront esf = new ShotFront(phaseshift,director,ship.referenced().transform());
        var bp = ship.getBagPack();
        ship.addWeapon(esf);
        FireFrequency frqsh = new FireFrequency(40,bp);
        ship.addBehaviour(frqsh);
        ship.referenced().transform().setTop(Vector2.DOWN());
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
        if(Random.bool())
            ship.addBehaviour(new LookTarget(PlayerShip.getInstance().referenced().transform()));
    }

    @Override
    public void assembleData()
    {
        ship.setData(GameSettings.GetInstance().TieData.clone());
    }
}
