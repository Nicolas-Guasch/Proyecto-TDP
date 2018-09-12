package Entities;

import Engine.Component;
import InputManager.*;

public class SoloController extends Component
{
    private final Ship solo;
    private AbstractContinueInput W,A,S,D;
    private AbstractDiscreteInput E,X,Space,Shoot;
    private float Speed=16; // solo para testear
    private float ShootPower=8; // solo para testear

    public SoloController (Ship solo)
    {
        W = new ContinueKeyInput("wW");
        A = new ContinueKeyInput("aA");
        S = new ContinueKeyInput("sS");
        D = new ContinueKeyInput("dD");
        E = new DiscreteKeyInput("eE");
        Space = new DiscreteKeyInput(" ");
        Shoot = new DiscreteClick(1);
        this.solo = solo;
    }

    @Override
    public void Start() {
        Shoot.OnAction().Suscribe(this::Shoot);
    }

    private void Shoot(Boolean isPressed)
    {
        if(isPressed)
        {
            solo.weapon().Shoot();
        }
    }

    @Override
    public void Update()
    {

    }
}
