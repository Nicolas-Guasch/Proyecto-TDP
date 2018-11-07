package Tools;

import ADTs.Vector2;
import Audio.SoundManager;
import Engine.Components.Transform;
import Engine.GameObject;
import RenderingSystem.Animation;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

public class AnimatorsVolatiles
{
    private static AnimatorsVolatiles instance = new AnimatorsVolatiles();

    public static AnimatorsVolatiles getInstance(){
        return instance;
    }

    private String explosionOptions[] = {"exploA","exploB"};
    private GameObject parent;
    private boolean swapper = true;

    private AnimatorsVolatiles(){
        parent = GameObject.getRoot().addChild();
    }

    public Transform getVolatile(Vector2 position, String exp, float speed){
        var g = parent.addChild();
        var rend = new Renderizable(new SpriteData(exp+"_0"));
        g.setRenderer(rend);
        rend.show();

        Animation anim = new Animation(exp, rend,true);
        anim.setSpeed(speed);
        g.addComponent(anim);
        g.transform().setPosition(position);
        swapper = !swapper;
        g.transform().setTop(swapper ? Vector2.DOWN() : Vector2.UP());
        return g.transform();
    }


    public Transform getExplosion(Vector2 position){
        String exp = Tools.random(explosionOptions);
        var t = getVolatile(position,exp,45);
        SoundManager.Instance().explo(position);
        return t;
    }



}
