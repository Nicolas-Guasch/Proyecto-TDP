package Tools;

import ADTs.IVector2;
import ADTs.Vector2;
import Audio.SoundManager;
import Engine.Components.ITransform;
import Engine.GameObject;
import Engine.IGameObject;
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
    private IGameObject parent;
    private boolean swapper = true;

    private AnimatorsVolatiles(){
        parent = GameObject.getRoot().addChild();
    }

    public ITransform getVolatile(IVector2 position, String exp, float speed){
        IGameObject g = parent.addChild();
        Renderizable rend = new Renderizable(new SpriteData(exp+"_0"));
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


    public ITransform getExplosion(IVector2 position){
        String exp = Tools.random(explosionOptions);
        ITransform t = getVolatile(position,exp,45);
        SoundManager.Instance().explo(position);
        return t;
    }



}
