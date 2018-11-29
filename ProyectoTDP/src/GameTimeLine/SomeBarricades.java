package GameTimeLine;

import ADTs.IVector2;
import ADTs.Vector2;
import Engine.Components.ITransform;
import Entities.BarricadeBoth;
import Entities.Builders.Directors.BarricadeBothDirector;
import Entities.Entity;
import Entities.EveryOne;
import Entities.Barricades.BlueBarricadeMaker;
import GameData.GameSettings;

import java.awt.*;

public class SomeBarricades extends TimePoint {

    private BarricadeBothDirector director;

    public SomeBarricades(){

        director = new BarricadeBothDirector();
        director.setBuilder(new BlueBarricadeMaker());
    }

    private Entity nextBarricade(){
        director.create();
        director.assemble();
        BarricadeBoth barr = director.get();
        return barr;
    }

    @Override
    public void assembleMoment() {
        int circleBarricades = 20;
        float angle = 1f/circleBarricades;
        Dimension dim = GameSettings.GetInstance().sizeWindow;
        float rad= Math.min(dim.width,dim.height) * 0.3f;
        IVector2 pos = Vector2.UP(rad);
        for(int i=0 ; i<circleBarricades ; i++){
            Entity entBar = nextBarricade();
            ITransform bar = entBar.referenced().transform();
            bar.setPosition(pos);
            bar.setTop(pos.prod(-1));
            pos = pos.rotateUnary(angle);
            EveryOne.getInstance().add(entBar);
            entBar.data().setShield(0.8f);
        }
    }

    @Override
    public void startMoment() {

    }

    @Override
    public boolean completed() {
        return true;
    }
}
