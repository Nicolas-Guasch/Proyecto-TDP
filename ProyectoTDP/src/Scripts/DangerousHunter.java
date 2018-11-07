package Scripts;

import Engine.Component;
import Engine.Components.Transform;

public class DangerousHunter extends Component
{
    private final float increment;
    private final float maxSpeed;
    private Transform toFollow;
    private float currentSpeed;

    /**
     *
     * @param toFollow something to follow
     * @param initialSpeed initial speed
     * @param increment
     *      if the value is greater than 1 the speed will increase
     *      if the value is less than 1, the speed will decrease
     *      if it is 1 it will remain constant
     * @param maxSpeed max velocity
     */
    public DangerousHunter(Transform toFollow, float initialSpeed, float increment, float maxSpeed) {
        this.toFollow = toFollow;
        this.currentSpeed =initialSpeed;
        this.increment = increment;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void update()
    {
        if(currentSpeed<maxSpeed && currentSpeed>0)
        {
            currentSpeed *= increment;
        }
        transform().setTop(toFollow.position().sub(transform().position()));
        transform().moveTowards(transform().top(currentSpeed));
    }
}
