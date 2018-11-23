package Engine;

import java.util.concurrent.Callable;

/**
 * Implements a concurrent While Cycle
 * It will make a loop in each frame until the condition returns false
 */
public class While extends Component
{
    private Condition condition; //fixme uml (condition)
    private Action codeBlock;//fixme uml action


    private boolean cond;
    private boolean runs;
    private Action onComplete;

    public While(Condition condition, Action codeBlock) { //fixme uml (condition)
        GameObject.getRoot().addChild().addComponent(this);
        this.condition = condition;
        this.codeBlock = codeBlock;
        cond = false;
        runs = false;
    }


    //nota: charlar con un ayudante la visibilidad del metodo
    @Override
    public void update() {
        boolean check = checks();
        if(check && cond)
        {
            codeBlock.invoke();
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
                onComplete.invoke();
            }
        }
    }

    private Boolean checks()
    {
        return condition.ask();

    }


    public void Excecute()
    {
        cond = true;
        update();
    }

    public void OnComplete(Action onComplete) {
        this.onComplete = onComplete;
    }
}
