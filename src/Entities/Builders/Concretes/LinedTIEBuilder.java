package Entities.Builders.Concretes;

import Engine.Components.RectangleCollider;
import Engine.Vector2;
import Entities.Behaviours.EnemyShootFront;
import Entities.Behaviours.FreqShoot;
import Entities.Behaviours.LinedEnemy;
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
import Stuff.Paths;
import Tools.Random;
import UtilsBehaviours.MirrorBounds;

public class LinedTIEBuilder extends EnemyShipBuilder
{

    public final static SpriteData SPRITEDATA = new SpriteData(Paths.WHITETIE,new Vector2(200,200));
    public final static SpriteData SPRITEDATA2 = new SpriteData(Paths.Vader,new Vector2(200,200));
    private SpriteData prefSpriteData = new SpriteData(Paths.TIE2,new Vector2(200,200));

    public LinedTIEBuilder()
    {
        prefSpriteData = SPRITEDATA;
    }


    @Override
    public void assembleSprite()
    {
        Renderizable rend = Random.bool()? new Renderizable(SPRITEDATA) : new Renderizable(SPRITEDATA2);
        ship.setRenderer(rend);
        rend.Show();
    }

    @Override
    public void assembleCollider()
    {
        RectangleCollider rec = new RectangleCollider(new Vector2(40,40),ship);
        ship.setCollider(rec);
    }

    @Override
    public void assembleBehaviours() {
        ship.addBehaviour(new LinedEnemy(GameSettings.GetInstance().TieSpeed,101));
        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new TieBulletBuilder(ship.getReferenced().getTransform()));
        int phaseshift = (prefSpriteData==SPRITEDATA2)?26:18;
        EnemyShootFront esf = new EnemyShootFront(phaseshift,director,ship.getReferenced().getTransform());
        var bp = ship.getBagpack();
        ship.addWeapon(esf);
        FreqShoot frqsh = new FreqShoot(40,bp);
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
