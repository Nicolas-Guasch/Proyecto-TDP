package Entities.Barricades;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Entities.Builders.BarricadeEnemBuilder;
import Entities.EntityData;
import RenderingSystem.Animation;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

//el obstaculo al que solo le pega el player
public class OrangeBarricadeMaker extends BarricadeEnemBuilder {
    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(new SpriteData("bforcefield_0"));
        obst.setRenderer(rend);
        rend.show();
        Animation anim = new Animation("bforcefield", rend);
        anim.setSpeed(50);
        obst.addBehaviour(anim);
        Runnable ondie = new ThenVolatile(obst.referenced().transform(), "bfexplo");
        obst.setOnDeath(ondie);
    }



    @Override
    public void assembleCollider()
    {
        HitBox hb = HitBox.getOne(350,50,obst);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.BARRICADE_ENEM);
        obst.setHitBox(hb);
    }

    @Override
    public void assembleBehaviours()
    {
    }

    @Override
    public void assembleData() {
        obst.setData(EntityData.WithEqualsValues(200));
        obst.data().setShield(0);
    }
}
