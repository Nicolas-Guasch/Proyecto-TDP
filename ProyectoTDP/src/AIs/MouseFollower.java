package AIs;

import Engine.Component;
import InputManager.AbstractDirectionalInput;
import InputManager.DirectionalMouse;

/**
 * Player Ship behaviour
 * Ships top always look to the mouse
 */
public class MouseFollower extends Component
{

    private float angle;
    private AbstractDirectionalInput Dir;


    public MouseFollower(float angle) {
        this.angle = angle;

    }

    @Override
    public void start()
    {
        Dir = new DirectionalMouse(transform());
    }

    @Override
    public void update()
    {
        transform().setTop(Dir.Direction().rotateUnary(angle));
    }

    @Override
    public void OnDestroy()
    {
        Dir.Destroy();
    }
}
