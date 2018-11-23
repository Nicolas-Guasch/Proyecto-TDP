package Engine;

import java.util.concurrent.Callable;


/**
 * ensures that an action is executed at the moment when a condition is fulfilled
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
        this.condition = condition;
        this.codeBlock = action;
        cond = true;
    }

    @Override
    public void update() {

        if (cond && checks())
        {
            codeBlock.invoke();
            cond = false;

            gameObject().destroy();
        }
    }

    private Boolean checks() {
        return condition.ask();
    }

    


}