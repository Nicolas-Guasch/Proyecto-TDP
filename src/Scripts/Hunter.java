package Scripts;

import Engine.Component;
import Engine.Components.Transform;
import Engine.Vector2;

public class Hunter extends Component
{
    private final float speed;
    private Transform toFollow;

    public Hunter(Transform toFollow, float speed) {
        this.toFollow = toFollow;
        this.speed  =speed;
    }

    @Override
    public void Update() {
        Vector2 dir = toFollow.position().minus(transform().position()).withLength(speed);
        transform().MoveTowards(dir);
    }
}

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
    public void Update()
    {
        if(currentSpeed<maxSpeed && currentSpeed>0)
        {
            currentSpeed *= increment;
        }
        Vector2 dir = toFollow.position().minus(transform().position()).withLength(currentSpeed);
        transform().MoveTowards(dir);
    }
}