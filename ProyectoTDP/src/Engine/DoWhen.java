package Engine;



/**
 * ensures that an action is executed at the moment a condition is fulfilled
 */
public class DoWhen extends Component {
    private Condition condition;
    private Action codeBlock;


    private boolean cond;

    /**
     *
     * @param condition the condition to be fulfilled
     * @param action the action to run
     */



    public DoWhen(Condition condition, Action action) {

        GameObject.getRoot().addChild().addComponent(this);
        this.condition = condition; // no chequeo nulo aca
        this.codeBlock = action;
        cond = true;
    }


    @Override
    public void update() {

        if (cond && condition != null && condition.ask())
        {
            if(codeBlock!=null)
                codeBlock.invoke();
            cond = false;
            gameObject().destroy();
        }
    }



}