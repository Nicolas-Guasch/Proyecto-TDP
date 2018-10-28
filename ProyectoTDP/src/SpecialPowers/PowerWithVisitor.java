package SpecialPowers;

import Entities.EveryOne;
import EntitiesVisitor.VisitorEntity;


public class PowerWithVisitor implements ISpecialPower
{

    private  VisitorEntity effect;

    public PowerWithVisitor(VisitorEntity effect){
        this.effect = effect;
    }

    @Override
    public void aply() {
        EveryOne.getInstance().takeLazyVisitor(effect);
    }
}
