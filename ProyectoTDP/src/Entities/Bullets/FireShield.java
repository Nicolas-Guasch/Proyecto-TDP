package Entities.Bullets;

import Engine.IGameObject;
import Entities.Bullet;
import EntitiesVisitor.VisitorEntity;

public class FireShield extends Bullet
{
    protected FireShield(IGameObject referenced) {
        super(referenced);
    }

    @Override
    public void accept(VisitorEntity visitor){
        visitor.visit(this);
    }
}
