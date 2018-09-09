package ParaTestear;

import Engine.Component;
import Engine.Vector2;
import Engine.GameObject;
import InputManager.*;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

import java.util.Random;

public class ComportamientoTester extends Component
{
    private AbstractContinueInput W,A,S,D, ClickCont;
    private AbstractDiscreteInput E,X,Space,Shoot;
    private float Speed=16;
    private float ShootPower=8;

    public ComportamientoTester()
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
    public void Start() {
        Shoot.OnAction().Suscribe((b)->
        {
            if(b){
                Shoot();
            }
        });

        X.OnAction().Suscribe((b)->
        {
            if(b)
            {
                Shoot();
            }
        });
    }

    public void Shoot()
    {
        LaserMaker.createBlue(gameObject(),ShootPower);

    }


    public void Update()
    {
        Vector2 move = Vector2.ORIGIN();
        Vector2 top = transform().getTop();


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
