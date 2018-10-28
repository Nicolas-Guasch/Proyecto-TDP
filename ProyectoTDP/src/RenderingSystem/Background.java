package RenderingSystem;

import ADTs.Vector2;
import Engine.Component;
import Engine.GameObject;
import Engine.While;
import GameData.GameSettings;

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

    private boolean tweening = false;
    public void setSpeedTweened(float speed){
        if(tweening) return;

        tweening = true;
        While wh;
        if(speed > speedBackground){
            wh = new While(()-> speed > speedBackground,()->{
                float v = getSpeedBackground();
                v*=1.01f;
                setSpeedBackground(v);
            });
        }
        else{
            wh = new While(()-> speed < speedBackground,()->{
                float v = getSpeedBackground();
                v*=0.99f;
                setSpeedBackground(v);
            });
        }
        wh.Excecute();
        wh.OnComplete(()-> tweening = false);

    }

    public void setSpeedBackground(float speedBackground){
        this.speedBackground = speedBackground;
    }

    public void setBG(String name){
        var sd = new SpriteData(name);
        gameObject().getRenderer().Sprite().setIcon(sd.icon());
        backgroundHeight = sd.icon().getIconHeight();
        transform().setPosition(Vector2.UP(phaseShift()));
    }


    public void start(){
        var sd = new SpriteData("fondo_tatooine");
        backgroundHeight = sd.icon().getIconHeight();
        var rend = new Renderizable(sd);
        gameObject().setRenderer(rend);
        rend.show();
        transform().setPosition(Vector2.UP(phaseShift()));
        Window.GetInstance().setAsBackground(rend);
    }

    private float phaseShift() {
        return (backgroundHeight - GameSettings.GetInstance().sizeWindow.height)/2f;
    }

    @Override
    public void update() {
        if(transform().position().y()<-phaseShift()){
            transform().setPosition(Vector2.UP(phaseShift()));
        }
        transform().moveTowards(Vector2.DOWN(speedBackground/8));
    }

    public float getSpeedBackground() {
        return speedBackground;
    }
}
