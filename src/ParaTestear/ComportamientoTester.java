package ParaTestear;

import Engine.Component;
import Engine.Components.Vector2;
import Engine.GameObject;
import InputManager.ContinueKeyInput;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        tirito.addComponent(new Tirito(Vector2.UP().prod(ShootPower),oper));
        Renderizable rend = new Renderizable(new SpriteData(Paths.NaveTester,new Vector2(20,20)));
        rend.Show();

        tirito.addComponent(rend);

        tirito.getTransform().setPosition(transform().getPosition().sum(new Vector2(35,20)));
        System.out.println("tirito");
    }


    public void Update()
    {
        float x=0;
        float y=0;
        if(W.Happens())
        {
            y++;
        }
        if(A.Happens())
        {
            x--;
        }
        if(S.Happens())
        {
            y--;
        }
        if(D.Happens())
        {
            x++;
        }
        transform().MoveTowards(new Vector2(x,y).versor().prod(Speed));
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
