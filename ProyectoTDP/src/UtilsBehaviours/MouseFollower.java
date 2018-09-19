package UtilsBehaviours;

import Engine.Component;
import InputManager.DirectionalMouse;

public class MouseFollower extends Component
{
    private DirectionalMouse Dir;
    public MouseFollower()
    {

    }

    @Override
    public void Start() {
        Dir = new DirectionalMouse(transform());
    }

    @Override
    public void Update()
    {
        transform().setTop(Dir.Direction());
    }
}
