package GameMaster;

import GUI.IUpdatable;
import Map.Map;

import java.util.concurrent.Callable;

public class While implements IUpdatable
{
    private Callable<Boolean> Condition;
    private Runnable Action;
    private boolean excecuted;
    private boolean completed;
    public While(Callable<Boolean> condition, Runnable action)
    {
        Condition = condition;
        Action = action;
        excecuted = false;
        completed = false;
        Map.getInstance().add(this);
    }


    @Override
    public void update(Map map)
    {
        boolean cond;
        try {
            cond = Condition.call();
        } catch (Exception e) {cond = false;}
        if(cond && !completed)
        {
            excecuted = true;
            Action.run();
        }
        if(!cond && excecuted ){
            completed = true;
            map.remove(this);
        }
    }

    @Override
    public void destroyMe(Map map) {

    }
}
