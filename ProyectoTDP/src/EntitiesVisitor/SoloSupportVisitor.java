package EntitiesVisitor;

import ADTs.Vector2;
import Audio.SoundManager;
import Engine.DoWhen;
import Engine.GameObject;
import Entities.*;
import AIs.FireFrequency;
import Entities.Builders.Concretes.BulletPlayerBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Ships.Player.PlayerShip;
import Entities.Ships.Ship;
import Entities.Weapons.EnemyArsenal;
import Entities.Weapons.ShotFront;
import IAs.*;
import RenderingSystem.Renderizable;
import RenderingSystem.ShadowedRend;
import RenderingSystem.SpriteData;

public class SoloSupportVisitor extends VisitorEntity
{

    private Entity entity;
    private boolean made = false;
    private Ship ship;

    public void visit(PlayerShip player){
        if(made)return;
        entity.data().setHealth(-1);
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

                new Vector2(-540,300),
                Vector2.random().withLength(600),
                new Vector2(150,-400),
                Vector2.random().withLength(500),
                new Vector2(-240,300),
                Vector2.random().withLength(500),
                new Vector2(10,00),
                Vector2.random().withLength(400),
                new Vector2(-640,300),
                Vector2.random().withLength(500),
                new Vector2(-240,-300),
                Vector2.random().withLength(500),

                new Vector2(-800,0),
        };

        SoundManager.Instance().Radio();

        EntityQuery handler = new DummyEntityQuery();
        var soloai = new SoloAI(handler,waypoints);
        soloai.searchSomeEnemies(6);
        handler = soloai;
        //handler = new Slippery(handler);
        ship.setPilot(new Pilot(handler,ship,15));
        ship.addBehaviour(new FireFrequency(15,ship.getArsenal()));
        ship.referenced().transform().setPosition(new Vector2(00,200));
        EveryOne.getInstance().add(ship);
        new DoWhen(()->ship.referenced().transform().position().distanceTo(new Vector2(-800,00))<17,()->{
            ship.getPilot().setActive(false);
            ship.getArsenal().setActive(false);
            ship.data().setHealth(-1);
        });



    }

    private void getRenderer() {
        Renderizable rend = new ShadowedRend(new SpriteData("soloship"));
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
