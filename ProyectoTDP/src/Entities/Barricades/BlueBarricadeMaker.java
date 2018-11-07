package Entities.Barricades;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Entities.Builders.BarricadeBothBuilder;
import Entities.EntityData;
import RenderingSystem.Animation;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

public class BlueBarricadeMaker extends BarricadeBothBuilder {

    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(new SpriteData("forcefield_0"));
        obst.setRenderer(rend);


        Animation  anim = new Animation("forcefield",rend);
        anim.setSpeed(40);
        obst.addBehaviour(anim);
        rend.show();

        Runnable
        ondie = new ThenVolatile(obst.referenced().transform(), "fexplo");
        obst.setOnDeath(ondie);
    }

    @Override
    public void assembleCollider()
    {
        HitBox hb = HitBox.getOne(350,100,obst);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.BARRICADE_BOTH);
        obst.setHitBox(hb);
    }

    @Override
    public void assembleBehaviours()
    {

    }

    @Override
    public void assembleData() {
        obst.setData(EntityData.WithEqualsValues(800));
        obst.data().setShield(0.1f);
    }
}
