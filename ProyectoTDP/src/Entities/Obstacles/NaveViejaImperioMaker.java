package Entities.Obstacles;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Entities.Builders.Directors.ObstacleMonoDirectionalDirector;
import Entities.Builders.ObstacleBidirectionalBuilder;
import Entities.Builders.ObstacleMonoDirectionalBuilder;
import Entities.EntityData;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

//el obstaculo al que solo le pega el player
public class NaveViejaImperioMaker extends ObstacleMonoDirectionalBuilder {
    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(new SpriteData("obstaclemono"));
        obst.setRenderer(rend);
        rend.show();
    }

    @Override
    public void assembleCollider()
    {
        HitBox hb = HitBox.getOne(150,50,obst);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.BARRICADE_B);
        obst.setHitBox(hb);
    }

    @Override
    public void assembleBehaviours()
    {
    }

    @Override
    public void assembleData() {
        obst.setData(EntityData.WithEqualsValues(800));
    }
}
