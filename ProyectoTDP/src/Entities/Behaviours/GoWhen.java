package Entities.Behaviours;

import Engine.Component;
import GameData.SoundManager;

import java.util.concurrent.Callable;

public class GoWhen extends Component
{


    Callable<Boolean> condition;
    private float speed;

    public GoWhen(Callable<Boolean> condition, float speed)
    {
        this.condition = condition;
        this.speed = speed;
    }

    @Override
    public void Update() {
        boolean cond;
        try {
            cond = condition.call();
        } catch (Exception e) {
            cond = false;
        }
        if(cond)
        {
            SoundManager.Instance().TieEngine(transform().position());
            transform().MoveTowards(transform().top(speed));
        }
    }
}
