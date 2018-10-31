package Engine;

import java.util.concurrent.Callable;

/**
 * Implements a concurrent While Cycle
 * It will make a loop in each frame until the condition returns false
 */
public class While extends Component
{
    private Callable<Boolean> condition;
    private Runnable codeBlock;


    private boolean cond;
    private boolean runs;
    private Runnable onComplete;

    public While(Callable<Boolean> condition, Runnable codeBlock) {
        GameObject.getRoot().addChild().addComponent(this);
        this.condition = condition;
        this.codeBlock = codeBlock;
        cond = false;
        runs = false;
    }

    @Override
    public void update() {
        boolean check = checks();
        if(check && cond)
        {
            codeBlock.run();
            runs = true;
        }
        else
        {
            cond=false;
        }
        if(runs && !check) // si salio del ciclo while
        {
            gameObject().destroy();
            if(onComplete!=null){
                onComplete.run();
            }
        }
    }

    private Boolean checks()
    {
        try {
            return condition.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // if call throws an exception condition must be false for me
    }


    public void Excecute()
    {
        cond = true;
        update();
    }

    public void OnComplete(Runnable onComplete) {
        this.onComplete = onComplete;
    }
}
