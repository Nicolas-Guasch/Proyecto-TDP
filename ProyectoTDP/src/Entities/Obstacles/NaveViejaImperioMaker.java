package Entities.Obstacles;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Entities.Builders.BarricadeEnemBuilder;
import Entities.EntityData;
import RenderingSystem.Animation;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

//el obstaculo al que solo le pega el player
public class NaveViejaImperioMaker extends BarricadeEnemBuilder {
    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(new SpriteData("bforcefield_0"));
        obst.setRenderer(rend);
        rend.show();
        Animation anim = new Animation("bforcefield", rend);
        anim.setSpeed(40);
        obst.addBehaviour(anim);
    }

    @Override
    public void assembleCollider()
    {
        HitBox hb = HitBox.getOne(350,50,obst);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.BARRICADE_B);
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
