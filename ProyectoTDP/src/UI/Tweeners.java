package UI;

import ADTs.Vector2;
import Engine.Action;
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


    //fixme (param)
    public void DoMove(Transform toMove, Vector2 destiny, int steps, Action onComplete){
        Action completion = new Action() {
            @Override
            public void invoke() {
                if (onComplete != null) {
                    onComplete.invoke();//ver si necesito cosas extras
                }
            }
        };
        toMove.setPosition(destiny);
        //mine.addComponent(new TweenerMove(toMove,destiny,steps,completion));
    }
}
