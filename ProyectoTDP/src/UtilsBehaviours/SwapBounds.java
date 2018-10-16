package UtilsBehaviours;

import Engine.Component;
import ADTs.Vector2;
import GameData.GameSettings;


//TODO: combinar con MirrorBounds y hacer uno que use todas las cualidades
public class SwapBounds extends Component
{
    private static float h = GameSettings.GetInstance().sizeWindow.height;
    private static float w = GameSettings.GetInstance().sizeWindow.width;
    private float paddingX,paddingY;
    private Vector2 v;
    public SwapBounds(float letX, float letY)
    {
        this.paddingX = letX;
        this.paddingY = letY;
    }

    public void Update()
    {
        v = transform().position();
        if(v.x()>(w/2)+2*paddingX)
        {
            transform().setPosition(new Vector2(-(w/2)-paddingX,v.y()));
        }
        if(v.x()<-(w/2)-2*paddingX){
            transform().setPosition(new Vector2((w/2)+paddingX,v.y()));
        }
        if(v.y()>(h/2)+2*paddingY){
            transform().setPosition(new Vector2(v.x(),-(h/2)-paddingY));
        }
        if(v.y()<-(h/2)-2*paddingY){
            transform().setPosition(new Vector2(v.x(),(h/2)+paddingY));
        }
    }
}
