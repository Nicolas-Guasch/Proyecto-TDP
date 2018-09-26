package GameMaster;

import GUI.IUpdatable;
import Map.Map;

import java.util.concurrent.Callable;

public class DoWhen implements IUpdatable
{
    private Callable<Boolean> Condition;
    private Runnable Action;
    private boolean excecuted;
    public DoWhen(Callable<Boolean> condition, Runnable action)
    {
        Condition = condition;
        Action = action;
        excecuted = false;
        Map.getInstance().add(this);
    }


    @Override
    public void update(Map map)
    {
        boolean cond;
        try {
            cond = Condition.call();
        } catch (Exception ce) {cond = false;}
        if(cond && !excecuted)
        {
            excecuted = true;
            Action.run();
        }
        if(excecuted){
            map.remove(this);
        }
    }

    @Override
    public void destroyMe(Map map) {

    }
}