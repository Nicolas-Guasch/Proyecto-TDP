package GameObjects;

import Assets.Configs;
import Assets.Paths;
import Map.Map;

import javax.swing.*;


public abstract class Enemy extends Ship{

    protected int score;

    @Override
    protected void updatePosition(Map map) {
        map.onUpdate(this);
        float x = ubication.getX();
        float y = ubication.getY();


        x += dir.getX() * speed;
        if(x < -12) //treshold del sprite, adecuar al sprite final
            x = -12;
        if (x > 1000)
            x = 1000;

        y += dir.getY() * speed;
        if(y < 0) //treshold del sprite, adecuar al sprite final
            y = 0;
        if (y > Configs.getConfigs().canvasHeight - 220)
            y = Configs.getConfigs().canvasHeight - 220;

        ubication = new Vector2(x,y);
    }
}
