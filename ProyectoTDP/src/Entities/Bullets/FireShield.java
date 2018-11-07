package Entities.Bullets;

import Engine.GameObject;
import Entities.Bullet;
import EntitiesVisitor.VisitorEntity;
import EntitiesVisitor.FireShieldEffect;
import RenderingSystem.Renderizable;

public class FireShield extends Bullet
{
    protected FireShield(GameObject referenced) {
        super(referenced);
    }

    @Override
    public void accept(VisitorEntity visitor){
        visitor.visit(this);
    }
}
