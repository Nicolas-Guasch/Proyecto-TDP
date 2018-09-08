package UtilsBehaviours;

import Engine.Component;
import Engine.Vector2;
import InputManager.DirectionalMouse;
import RenderingSystem.RenderingTools;

import java.awt.*;

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
