package Scripts;

import ADTs.Vector2;
import Engine.Component;
import Engine.GameObject;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import RenderingSystem.Window;

public class Background extends Component
{

    private static Background instance;
    public static Background getInstance(){
        if(instance ==null){
            instance = new Background();
            GameObject.getRoot().addChild().addComponent(instance);
        }
        return instance;
    }

    private float speedBackground = 0.01f;
    private float backgroundHeight = 1080;

    public void setSpeedBackground(float speedBackground){
        this.speedBackground = speedBackground;
    }

    public void start(){
        var rend = new Renderizable(new SpriteData("fondobig"));
        gameObject().setRenderer(rend);
        rend.show();
        transform().setPosition(Vector2.UP(-backgroundHeight));
        Window.GetInstance().setAsBackground(rend);

    }

    @Override
    public void update() {
        if(transform().position().y()<-backgroundHeight){
            transform().setPosition(Vector2.UP(backgroundHeight));
        }
        transform().moveTowards(Vector2.DOWN(speedBackground));
    }
}
