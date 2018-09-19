package Engine;

import java.util.concurrent.Callable;

public class While extends Component
{
    private Callable<Boolean> condition;
    private Runnable codeBlock;


    private boolean cond;

    public While(Callable<Boolean> condition, Runnable codeBlock) {
        this.condition = condition;
        this.codeBlock = codeBlock;
        cond = false;
    }

    @Override
    public void Update() {

        if(checks()&&cond)
        {
            codeBlock.run();
        }
        else
        {
            cond=false;
        }
    }

    private Boolean checks()
    {
        try {
            return condition.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void Excecute()
    {
        cond = true;
        Update();
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
