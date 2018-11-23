package RenderingSystem;

import ADTs.Vector2;
import Engine.*;
import Engine.Component;
import GameData.GameSettings;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


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
            wh = new While(new Condition() {
                @Override
                public boolean ask() {
                    return speed > speedBackground;
                }
            }, new Action() {
                @Override
                public void invoke() {
                    float v = Background.this.getSpeedBackground();
                    v *= 1.01f;
                    Background.this.setSpeedBackground(v);
                }
            });
        }
        else{
            wh = new While(new Condition() {
                @Override
                public boolean ask() {
                    return speed < speedBackground;
                }
            }, this::decSpeed);
        }
        wh.Excecute();
        wh.OnComplete(new Action() {
            @Override
            public void invoke() {
                tweening = false;
            }
        });
    }

    public void setSpeedBackground(float speedBackground){
        this.speedBackground = speedBackground;
    }

    private Map<Backgrounds, Color> colors = new HashMap<Backgrounds, Color>();
    private Background(){
        colors.put(Backgrounds.bg_sand,new Color(218, 186, 123));
        colors.put(Backgrounds.bg_space,new Color(0,0,0));
        colors.put(Backgrounds.bg_water,new Color(87, 215, 218));

    }
    public void setBG(Backgrounds name){
        SpriteData sd = new SpriteData(name.name());

        Window.GetInstance().setColor(colors.get(name));

        gameObject().getRenderer().Sprite().setIcon(sd.icon());
        backgroundHeight = sd.icon().getIconHeight();
        transform().setPosition(Vector2.UP(phaseShift()));
    }


    public void start(){
        SpriteData sd = new SpriteData(Backgrounds.bg_space.name());
        backgroundHeight = sd.icon().getIconHeight();
        Renderizable rend = new Renderizable(sd);
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
        transform().moveTowards(Vector2.DOWN(speedBackground/20));
    }

    public float getSpeedBackground() {
        return speedBackground;
    }

    //fixme << quitar metodo de aca y de UML (no se usa)
    public SpriteRenderer gerBG() {
        return gameObject().getRenderer().Sprite();
    }

    private void decSpeed() {//fixme
        float v = getSpeedBackground();
        v *= 0.99f;
        setSpeedBackground(v);
    }
}
