package Engine;



/**
 * Implements a Component which will run an Action while Condition is true
 * It will make a loop in each frame until the condition returns false
 */
public class While extends Component
{
    private Condition condition;
    private Action codeBlock;


    private boolean cond;
    private boolean runs;
    private Action onComplete;

    public While(Condition condition, Action codeBlock) {
        GameObject.getRoot().addChild().addComponent(this);
        this.condition = condition;
        this.codeBlock = codeBlock;
        cond = false;
        runs = false;
    }


    //La visibilidad del metodo tiene que ser publica, para respetar la interfaz IUpdatable. De todas formas necesita ser publico para poder ser visible para el engine, como con cualquier otro component
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

    private boolean checks()
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
