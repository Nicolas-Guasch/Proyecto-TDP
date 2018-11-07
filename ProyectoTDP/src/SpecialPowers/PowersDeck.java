package SpecialPowers;


import Engine.Components.IActivable;
import InputManager.AbstractDiscreteInput;


import java.util.Stack;


public class PowersDeck implements IActivable
{
    private Stack<ISpecialPower> powers;
    private boolean active = true;

    public PowersDeck(AbstractDiscreteInput activator){
        powers = new Stack<>();
        activator.OnAction().suscribe(this::use);
    }

    public void add(ISpecialPower power){
        powers.push(power);
    }

    private void use(boolean b){
        if(b && isActive() && powers.size()>0) {
            powers.pop().aply();
        }
    }


    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
