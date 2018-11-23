package UI;

import ADTs.IVector2;
import ADTs.Vector2;
import Engine.Component;
import Engine.Components.Transform;

public class TweenerMove extends Component
{


    private final int steps;
    private final Transform toMove;
    private final IVector2 destiny;
    private final Runnable onComplete;
    private int currentStep;

    public TweenerMove(Transform toMove, IVector2 destiny, int steps, Runnable onComplete){
        this.destiny = destiny;
        this.toMove = toMove;
        this.steps = steps;
        this.currentStep = 0;
        this.onComplete = onComplete;
    }

    //asumo currentStep != steps y
    private IVector2 direction(){
        return destiny.sub(toMove.position()).prod(1f/(steps-currentStep));
    }

    @Override
    public void update() {
        if (currentStep >= steps) {
            completion();
        }
        else{
            if(toMove.gameObject()==null){
                wasInterrupted();
            }
            toMove.moveTowards(direction());
        }
        currentStep++;
    }

    private void wasInterrupted(){
        setActive(false);
        DestroyComponent();
    }

    private void completion() {
        setActive(false);
        toMove.setPosition(destiny);
        if(onComplete!=null)
            onComplete.run();
        DestroyComponent();
    }
}
