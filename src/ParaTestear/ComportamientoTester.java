package ParaTestear;

import Engine.Component;
import Engine.Vector2;
import Engine.GameObject;
import InputManager.ContinueKeyInput;
import InputManager.DiscreteKeyInput;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

import java.util.Random;

public class ComportamientoTester extends Component
{
    private ContinueKeyInput W,A,S,D,Space;
    private DiscreteKeyInput E,X;
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
        Space = new ContinueKeyInput(" ");
    }

    @Override
    public void Start() {
        E.OnAction().Suscribe((b)->
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
        LaserMaker.create(gameObject(),Speed*ShootPower);

    }


    public void Update()
    {
        Vector2 move = Vector2.ORIGIN();


        Vector2 top = transform().getTop();
        if(A.Happens())
        {
            top = top.rotateUnary(0.001f*Speed);

        }
        if(D.Happens())
        {
            top = top.rotateUnary(-0.001f*Speed);
        }

        transform().setTop(top);



        if(W.Happens())
        {
            move = transform().getTop();
        }
        if(S.Happens())
        {
            move = move.minus(transform().getTop());
        }

        transform().MoveTowards(move.prod(Speed));


    }
}
