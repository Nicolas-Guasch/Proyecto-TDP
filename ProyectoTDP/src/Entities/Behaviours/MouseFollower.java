package Entities.Behaviours;

import Engine.Component;
import InputManager.AbstractDirectionalInput;
import InputManager.DirectionalMouse;

/**
 * Player Ship behaviour
 * Ships top always look to the mouse
 */
public class MouseFollower extends Component
{
    private AbstractDirectionalInput Dir;

    @Override
    public void start()
    {
        Dir = new DirectionalMouse(transform());
    }

    @Override
    public void update()
    {
        transform().setTop(Dir.Direction());
    }

    @Override
    public void OnDestroy()
    {
        Dir.Destroy();
    }
}
