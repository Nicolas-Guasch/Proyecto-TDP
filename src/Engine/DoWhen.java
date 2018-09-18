package Engine;

import java.util.concurrent.Callable;

public class DoWhen extends Component {
    private Callable<Boolean> condition;
    private Runnable codeBlock;


    private boolean cond;

    public DoWhen(Callable<Boolean> condition, Runnable codeBlock) {
        GameObject.getRoot().addChild().addComponent(this);
        this.condition = condition;
        this.codeBlock = codeBlock;
        cond = true;
    }

    @Override
    public void Update() {

        if (cond && checks())
        {
            codeBlock.run();
            cond = false;
            gameObject().Destroy();
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

    public boolean complete()
    {
        return !cond;
    }

    public Runnable getCodeBlock() {
        return codeBlock;
    }

    public void setCodeBlock(Runnable codeBlock) {
        this.codeBlock = codeBlock;
    }

    public Callable<Boolean> getCondition() {
        return condition;
    }

    public void setCondition(Callable<Boolean> condition) {
        this.condition = condition;
    }


}