package CuteThings;

import ADTs.Vector2;
import Audio.SoundManager;
import Engine.GameObject;
import RenderingSystem.Animation;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Tools.Tools;

public class Explos
{
    private static Explos instance = new Explos();

    public static Explos getInstance(){
        return instance;
    }
    private String options[] = {"exploA","exploB"};
    private GameObject parent;

    private Explos(){
        parent = GameObject.getRoot().addChild();
    }

    public void getVolatile(Vector2 position, String exp,float speed){
        var g = parent.addChild();
        var rend = new Renderizable(new SpriteData(exp+"_0"));
        g.setRenderer(rend);
        rend.show();

        Animation anim = new Animation(exp, rend,true);
        anim.setSpeed(speed);
        g.addComponent(anim);
        g.transform().setPosition(position);
    }

    public void getExplosion(Vector2 position){
        String exp = Tools.random(options);
        getVolatile(position,exp,45);
        SoundManager.Instance().explo(position);
    }



}
