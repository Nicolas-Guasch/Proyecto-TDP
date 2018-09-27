package Entities.Builders.Concretes;

import Collisions.CircleCollider;
import Engine.Vector2;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Builders.PlayerShipBuilder;
import Entities.PlayerBullet;
import Entities.Weapons.GenericalWeapon;
import GameData.GameSettings;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import UtilsBehaviours.MirrorBounds;
import Entities.Behaviours.MouseFollower;

public class PlayerBuilder extends PlayerShipBuilder
{

    public final static SpriteData SPRITEDATA = new SpriteData("shipplayer",new Vector2(200,200));
    private int cantShoots = 1;


    public PlayerBuilder(int cantShoots)
    {
        this.cantShoots = cantShoots;
    }


    @Override
    public void assembleSprite() {
        Renderizable rend = new Renderizable(SPRITEDATA);
        rend.Show();
        ship.setRenderer(rend);
    }

    @Override
    public void assembleCollider() {
        CircleCollider rec = new CircleCollider(new Vector2(135,135),ship);
        //RectangleCollider rec = new RectangleCollider(new Vector2(135,135),ship);
        ship.setCollider(rec);
    }

    @Override
    public void assembleBehaviours() {
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(ship.getReferenced().getTransform()));





        ship.addWeapon(new GenericalWeapon<>(ship.getReferenced().getTransform(),director,1));
        ship.addWeapon(new GenericalWeapon<>(ship.getReferenced().getTransform(),director,2));
        ship.addWeapon(new GenericalWeapon<>(ship.getReferenced().getTransform(),director,3));
        //ship.addWeapon(new LateralizedWeapon<>(ship.getReferenced().getTransform(),director,9));




        //ship.addBehaviour(new PlayerLateralController(GameSettings.GetInstance().SoloSpeed));
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
        ship.addBehaviour(new MouseFollower());
    }

    @Override
    public void assembleData() {
        ship.setData(GameSettings.GetInstance().PlayerData); //si, referencia
    }
}
