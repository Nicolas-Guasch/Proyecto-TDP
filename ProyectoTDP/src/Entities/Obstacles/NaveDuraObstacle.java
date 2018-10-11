package Entities.Obstacles;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Vector2;
import Entities.Builders.ObstacleBidirectionalBuilder;
import Entities.EntityData;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.AlwaysLateral;

public class NaveDuraObstacle extends ObstacleBidirectionalBuilder {

    @Override
    public void assembleSprite()
    {
        Renderizable rend = new Renderizable(new SpriteData("obstaclebi"));
        obst.setRenderer(rend);
        rend.show();
    }

    @Override
    public void assembleCollider()
    {
        HitBox hb = HitBox.getOne(200,100,obst);
        HitBoxesManager.getInstance().addHitBox(hb,HitBoxesManager.BARRICADE_A);
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
