package RenderingSystem;

import Engine.Vector2;
import GameData.GameSettings;

import java.awt.*;

public class RenderingTools
{
    private static float h = GameSettings.GetInstance().sizeWindow.height;
    private static float w = GameSettings.GetInstance().sizeWindow.width;


    //width es x , height es y
    public static Vector2 CanvasToWorld(Dimension d)
    {
        return new Vector2(d.width-w/2,(h/2)-d.height);
    }

    public static Dimension WorldToCanvas(Vector2 vec)
    {
        return new Dimension((int)(vec.x()+w/2) ,(int)((h/2)-vec.y()));
    }
}
