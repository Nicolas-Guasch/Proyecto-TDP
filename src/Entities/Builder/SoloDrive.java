package Entities.Builder;

import Engine.Component;
import Engine.Vector2;
import InputManager.*;
import ParaTestear.LaserMaker;

public class SoloDrive extends Component
{
    private AbstractContinueInput W,A,S,D, ClickCont;
    private AbstractDiscreteInput E,X,Space,Shoot;
    private float Speed=6;
    private float ShootPower=8;

    public SoloDrive()
    {
        W = new ContinueKeyInput("wW");
        A = new ContinueKeyInput("aA");
        S = new ContinueKeyInput("sS");
        D = new ContinueKeyInput("dD");
        E = new DiscreteKeyInput("eE");
        X = new DiscreteKeyInput("xX");
        Space = new DiscreteKeyInput(" ");
        Shoot = new DiscreteClick(1);
        ClickCont = new ContinueClick(1);
    }

    @Override
    public void Start()
    {

        Shoot.OnAction().Suscribe(this::shootNow);
        X.OnAction().Suscribe(this::shootNow);
    }

    private void shootNow(Boolean isPressed) {
        if (isPressed) {
            Shoot();
        }
    }

    public void Shoot()
    {
        LaserMaker.laserSolo(gameObject(),ShootPower);
    }


    public void Update()
    {
        Vector2 move = Vector2.ORIGIN();
        //Vector2 top = transform().getTop();


        if(A.Happens())
        {
            move = Vector2.LEFT(Speed);
        }
        if(D.Happens())
        {
            move = move.sum(Vector2.RIGHT(Speed));
        }

        transform().MoveTowards(move);


    }


}

