package SpecialPowers;

import Entities.EveryOne;
import EntitiesVisitor.VisitorEntity;
import org.jetbrains.annotations.NotNull;

public class PowerWithVisitor implements ISpecialPower
{
    @NotNull
    private  VisitorEntity effect;

    public PowerWithVisitor(VisitorEntity effect){
        this.effect = effect;
    }

    @Override
    public void aply() {
        EveryOne.getInstance().takeLazyVisitor(effect);
    }
}
