package Entities.Ships.Player;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import ADTs.Vector2;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.PlayerBullet;
import Entities.Weapons.GenericWeapon;
import Entities.Weapons.LateralWeapon;
import GameData.GameSettings;
import IAs.*;
import InputManager.DirectionalMouse;
import InputManager.DirectionalWASD;
import ADTs.Vector3;
import RenderingSystem.*;
import UtilsBehaviours.MirrorBounds;


// REQUIERE QUE LUEGO LE ACTIVES EL ARSENAL

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
        Renderizable rend = new ShadowedRend(data);
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
        DirectionalMouse direction = new DirectionalMouse(ship.referenced().transform());
        DirectionalWASD move = new DirectionalWASD();
        move.lockY();
        handler = new PlayerMove(handler,move,direction);
        ship.referenced().transform().setPosition(new Vector3(0, -700,-20));
        handler = new Slippery(handler);
        Pilot pilot = new Pilot(handler,ship,15f);
        ship.setPilot(pilot);

        ship.addBehaviour(new PlayerShipPerspective(ship.referenced().getRenderer()));

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



        director.setBuilder(new BulletPlayerBuilder(ship.referenced().transform()));
        var w2 = new GenericWeapon<>(ship.referenced().transform(),director,  2);
        w2.setName("bilaser");
        var w3 =new LateralWeapon<>(ship.referenced().transform(),director,  3);
        w3.setName("trilaser");
        ship.addWeapon(w2);
        ship.addWeapon(w3);

        ship.getArsenal().setActive(false);


    }


    public void assembleData()
    {
        ship.setData(GameSettings.GetInstance().PlayerData); //si, referencia
        ship.data().setShield(ship.data().getShield()/GameSettings.difficulty);
    }
}
