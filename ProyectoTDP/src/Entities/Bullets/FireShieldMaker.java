package Entities.Bullets;

import ADTs.Vector2;
import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Entities.Builders.PlayerBulletBuilder;
import Entities.EntityData;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.FireShieldEffect;
import RenderingSystem.Animation;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.AlwaysTurnAround;

public class FireShieldMaker extends PlayerBulletBuilder
{



    @Override
    public void assembleSprite() {
        Renderizable rend = new Renderizable(new SpriteData("fireshield"));
        rend.show();
        bullet.setRenderer(rend);

        Animation anim = new Animation("fireshield", rend);
        anim.setSpeed(5); // 60 es uno por frame, 0 es 1 por segundo
        bullet.addBehaviour(anim);


    }

    @Override
    public void assembleHitBox() {
        HitBox hb = HitBox.getOne(84,30,bullet);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.SHIELD);//si, aca
        bullet.setHitBox(hb);
    }

    @Override
    public void assembleBehaviours() {
        float angularSpeed = -0.1f; // si es negativo rota hacia la izquierda
                                    //podriamos randomizar eso..
                                    // pero quedaría mal el sprite
        int ratio = 100;
        Vector2 firstLateral = Vector2.UP();

        var beh = new AlwaysTurnAround(PlayerShip.getInstance().referenced().transform(),
                                        ratio, angularSpeed,firstLateral);
        bullet.addBehaviour(beh);
        bullet.setVisitor(new FireShieldEffect(bullet.data().clone()));
    }

    @Override
    public void assembleData() {
        bullet.setData(EntityData.WithEqualsValues(10));
    }
}
