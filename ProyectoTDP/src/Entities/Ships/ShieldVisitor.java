package Entities.Ships;

import Engine.EngineGetter;
import EntitiesVisitor.VisitorEntity;
import GenericVisitor.MonoVisitor;


public class ShieldVisitor extends VisitorEntity
{

    float prevShield;
    PlayerShip visitable;


    @Override
    public void visit(PlayerShip visitable)
    {
        this.visitable = visitable;
        prevShield = visitable.data().getShield();
        visitable.data().setShield(100000);
        Runnable restaurar = this::restaurar;


        EngineGetter.Instance().get().waitForFrames(restaurar,1200);

    }

    private void restaurar()
    {
        visitable.data().setShield(prevShield);
    }

}
