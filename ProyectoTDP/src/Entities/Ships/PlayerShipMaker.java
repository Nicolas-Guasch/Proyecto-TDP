package Entities.Ships;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Vector2;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.PlayerBullet;
import Entities.Weapons.GenericalWeapon;
import Entities.Weapons.LateralizedWeapon;
import GameData.GameSettings;
import IAs.DummyEntityQuery;
import IAs.EntityQuery;
import IAs.Pilot;
import IAs.PlayerMove;
import InputManager.DirectionalMouse;
import InputManager.DirectionalWASD;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import UtilsBehaviours.MirrorBounds;

public class PlayerShipMaker extends PlayerShipBuilder {

/*
asset: shipplayer
name : "ShipPlayer"
speed : 5
weapons:
collider: 130x130
 */





    public void assembleSprite() {
        SpriteData data = new SpriteData("shipplayer");
        Renderizable rend = new Renderizable(data);
        rend.show();
        ship.setRenderer(rend);
    }


    public void assembleHitBox()
    {
        HitBox hb = HitBox.getOne(130,130,ship);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.PLAYER);
        ship.setHitBox(hb);
    }


    public void assembleBehaviours()
    {
        // ------- Pilot stuff -----------
        EntityQuery handler = new DummyEntityQuery();
        DirectionalMouse direction = new DirectionalMouse(ship.getReferenced().getTransform());
        DirectionalWASD move = new DirectionalWASD();
        move.lockY();
        handler = new PlayerMove(handler,move,direction);
        Pilot pilot = new Pilot(handler,ship,10f);
        ship.setPilot(pilot);

        // ------- Mirror effect ----------

            //obtengo el ancho y alto de la pantalla y le doy un rectangulo para que se mueva
            //con una proporcionalidad x1.2 para que se pueda ir un poquito
            //al llegar a un extremo aparece por el opuesto

        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
    }


    public void assembleWeapons()
    {
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(ship.getReferenced().getTransform()));
        ship.addWeapon(new GenericalWeapon<>(ship.getReferenced().getTransform(),director,2));
        ship.addWeapon(new LateralizedWeapon<>(ship.getReferenced().getTransform(),director,3));

    }


    public void assembleData() {
        ship.setData(GameSettings.GetInstance().PlayerData); //si, referencia
    }
}
