package EntitiesVisitor;

import ADTs.Vector2;
import Audio.AudioManager;
import Audio.SoundManager;
import Engine.DoWhen;
import Engine.GameObject;
import Entities.*;
import Entities.Behaviours.FireFrequency;
import Entities.Builders.Concretes.BulletMaker;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Ships.PlayerShip;
import Entities.Ships.Ship;
import Entities.Weapons.EnemyArsenal;
import Entities.Weapons.ShotFront;
import Entities.Weapons.WeaponSet;
import GameData.GameSettings;
import IAs.*;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Rewards.Entity;
import UtilsBehaviours.MirrorBounds;

public class SoloSupportVisitor extends VisitorEntity
{

    private Entity entity;
    private boolean made = false;
    private Ship ship;

    public void visit(PlayerShip player){
        if(made)return;
        entity.Destroy();
        createHanSolo();
        made = true;
    }




    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    private void createHanSolo() {
        GameObject obj = GameObject.getRoot().addChild();
        ship = new OtherShip(obj,new EnemyArsenal());
        getWeapons();
        getRenderer();
        getData();
        getBehaviour();
    }

    private void getData() {
        ship.setData(EntityData.WithEqualsValues(1));
    }

    private void getBehaviour() {

        Vector2[] waypoints = {
                new Vector2(180,100),
                new Vector2(-540,300),
                new Vector2(150,-400),
                new Vector2(-240,300),
                new Vector2(10,00),
                new Vector2(-640,300),
                new Vector2(-240,-300),
                new Vector2(-800,0),
        };

        SoundManager.Instance().Radio();

        EntityQuery handler = new DummyEntityQuery();
        var soloai = new SoloAI(handler,waypoints);
        soloai.searchSomeEnemies(6);
        handler = soloai;
        //handler = new Slippery(handler);
        ship.setPilot(new Pilot(handler,ship,15));
        ship.addBehaviour(new FireFrequency(5,ship.getArsenal()));
        ship.referenced().transform().setPosition(new Vector2(00,200));
        EveryOne.getInstance().add(ship);
        new DoWhen(()->ship.referenced().transform().position().distanceTo(new Vector2(-800,00))<17,()->{
            ship.getPilot().setActive(false);
            ship.getArsenal().setActive(false);
            ship.data().setHealth(-1);
            System.out.println("SoloSupportVisitor::getBehaviour");
        });


        // ----------- TODO: quitar---------------
       /* Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
*/

    }

    private void getRenderer() {
        Renderizable rend = new Renderizable(new SpriteData("soloship"));
        rend.show();
        ship.setRenderer(rend);
    }

    private void getWeapons(){

        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(ship.referenced().transform()));

        ShotFront esf = new ShotFront(20,director,ship.referenced().transform());
        ship.addWeapon(esf);

    }
    
    


}