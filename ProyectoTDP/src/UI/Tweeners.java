package UI;

import ADTs.IVector2;
import Engine.Action;
import Engine.Components.Transform;
import Engine.GameObject;
import Engine.IGameObject;

public class Tweeners
{
    private static Tweeners instance;
    public static Tweeners getInstance(){
        if(instance==null){
            instance = new Tweeners();
        }
        return instance;
    }
    private IGameObject mine;
    private Tweeners(){
        mine = GameObject.getRoot().addChild();
    }


    //fixme (param). No está en uml. Se usa?
    public void DoMove(Transform toMove, IVector2 destiny, int steps, Action onComplete){
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
