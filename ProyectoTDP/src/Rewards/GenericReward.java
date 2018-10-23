package Rewards;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.GameObject;
import EntitiesVisitor.VisitorEntity;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.RewardMove;

public class GenericReward extends Entity {

    private Renderizable renderer;

    public GenericReward(GameObject referenced, VisitorEntity visitor, SpriteData sprite) {
        super(referenced);
        this.visitor = visitor;

        renderer = new Renderizable(sprite);
        referenced.setRenderer(renderer);

        var hitBox = HitBox.getOne(100,100,this);
        HitBoxesManager.getInstance().addHitBox(hitBox,HitBoxesManager.REWARDS);
        referenced.addHitBox(hitBox);
        referenced.addComponent(new RewardMove(29));
        renderer.show();
    }



    @Override
    public void accept(VisitorEntity visitor) {
        visitor.visit(this);
    }
}
