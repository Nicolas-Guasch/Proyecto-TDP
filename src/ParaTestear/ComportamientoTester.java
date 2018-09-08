package ParaTestear;

import Engine.Component;
import Engine.Vector2;
import Engine.GameObject;
import InputManager.ContinueKeyInput;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

import java.util.Random;

public class ComportamientoTester extends Component
{
    private ContinueKeyInput E,W,A,S,D,Space;
    private float Speed=1;
    private float ShootPower=8;

    public ComportamientoTester()
    {
        W = new ContinueKeyInput("wW");
        A = new ContinueKeyInput("aA");
        S = new ContinueKeyInput("sS");
        D = new ContinueKeyInput("dD");
        E = new ContinueKeyInput("eE");
        Space = new ContinueKeyInput(" ");
    }


    public void Shoot()
    {
        GameObject tirito = gameObject().addChild();
        int oper = new Random().nextInt(11);
        tirito.addComponent(new Tirito(transform().getTop().prod(ShootPower),oper));
        Renderizable rend = new Renderizable(new SpriteData(Paths.NaveTester,new Vector2(20,20)));
        rend.Show();

        tirito.addComponent(rend);
        tirito.getTransform().setPosition(transform().getPosition().sum(transform().getTop().prod(20)));

    }


    public void Update()
    {
        Vector2 move = Vector2.ORIGIN();
        if(W.Happens())
        {
            move = transform().getTop();
        }
        if(S.Happens())
        {
            move = move.minus(transform().getTop());
        }
        if(A.Happens())
        {
            move = move.sum(transform().getTop().rotateUnary(-.2f));
        }

        if(D.Happens())
        {
            move = move.sum(transform().getTop().rotateUnary(.2f));
        }
        transform().MoveTowards(move.prod(Speed));
        if(Space.Happens())
        {
            Speed*= 1.03f;
            System.out.printf("SPEED : %f\n",Speed);
        }
        if(E.Happens())
        {
            Shoot();
        }
    }


}
