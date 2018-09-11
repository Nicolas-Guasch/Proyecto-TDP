package InputSource;

import Engine.Components.Transform;
import InputManager.ContinueKeyInput;
import InputManager.DirectionalMouse;
import InputManager.DiscreteClick;
import InputManager.DummyContinueInput;

public class SoloShipInputSourceBuilder extends InputSourceBuilder
{
    private Transform soloShip;
    private boolean enableFullMove;


    public SoloShipInputSourceBuilder(Transform soloShip, boolean enableFullMove)
    {
        this.soloShip = soloShip;
        this.enableFullMove = enableFullMove;
    }

    @Override
    public void createLeft() {
        inpSource.setLeft(new ContinueKeyInput("dD"));
    }

    @Override
    public void createRight() {
        inpSource.setRight(new ContinueKeyInput("aA"));
    }

    @Override
    public void createUp() {
        if(enableFullMove)
            inpSource.setUp(new ContinueKeyInput("wW"));
        else
            inpSource.setUp(new DummyContinueInput(false));
    }

    @Override
    public void createDown() {
        if(enableFullMove)
            inpSource.setDown(new ContinueKeyInput("sS"));
        else
            inpSource.setDown(new DummyContinueInput(false));
    }

    @Override
    public void createFront() {
        inpSource.setFront(new DirectionalMouse(soloShip));
    }

    @Override
    public void createShoot() {
        inpSource.setShoot(new DiscreteClick(1));
        //TODO: si no funciona el click era otro numero
    }
}
