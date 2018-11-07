package Engine;

import java.util.concurrent.Callable;


/**
 * ensures that an action is executed at the moment when a condition is fulfilled
 */
public class DoWhen extends Component {
    private Callable<Boolean> condition;
    private Runnable codeBlock;

    private boolean cond;

    /**
     *
     * @param condition the condition to be fulfilled
     * @param action the action to run
     */
    public DoWhen(Callable<Boolean> condition, Runnable action) {
        GameObject.getRoot().addChild().addComponent(this);
        this.condition = condition;
        this.codeBlock = action;
        cond = true;
    }

    @Override
    public void update() {

        if (cond && checks())
        {
            codeBlock.run();
            cond = false;
            gameObject().destroy();
        }
    }

    private Boolean checks() {
        try {
            return condition.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    


}