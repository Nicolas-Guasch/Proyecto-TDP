package UI;

import ADTs.Vector2;
import Engine.Components.Transform;
import Engine.GameObject;

public class Tweeners
{
    private static Tweeners instance;
    public static Tweeners getInstance(){
        if(instance==null){
            instance = new Tweeners();
        }
        return instance;
    }
    private GameObject mine;
    private Tweeners(){
        mine = GameObject.getRoot().addChild();
    }


    public void DoMove(Transform toMove, Vector2 destiny, int steps, Runnable onComplete){
        Runnable completion = ()->{
            if(onComplete!=null) {
                onComplete.run();//ver si necesito cosas extras
            }
        };
        toMove.setPosition(destiny);
        //mine.addComponent(new TweenerMove(toMove,destiny,steps,completion));
    }
}
