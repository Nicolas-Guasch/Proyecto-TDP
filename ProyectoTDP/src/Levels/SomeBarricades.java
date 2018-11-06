package Levels;

import ADTs.Vector2;
import Entities.Builders.Directors.BarricadeBothDirector;
import Entities.Entity;
import Entities.EveryOne;
import Entities.Obstacles.BarricadeBlue;
import GameData.GameSettings;

public class SomeBarricades extends TimePoint {

    private BarricadeBothDirector director;

    public SomeBarricades(){

        director = new BarricadeBothDirector();
        director.setBuilder(new BarricadeBlue());
    }

    private Entity nextBarricade(){
        director.create();
        director.assemble();
        var barr = director.get();
        return barr;
    }

    @Override
    public void assembleMoment() {
        var circleBarricades = 10;
        float angle = 1f/circleBarricades;
        var dim = GameSettings.GetInstance().sizeWindow;
        var rad= Math.min(dim.width,dim.height) * 0.3f;
        Vector2 pos = Vector2.UP(rad);
        for(int i=0 ; i<circleBarricades ; i++){
            var entBar = nextBarricade();
            var bar = entBar.referenced().transform();
            bar.setPosition(pos);
            bar.setTop(pos.prod(-1));
            pos = pos.rotateUnary(angle);
            EveryOne.getInstance().add(entBar);
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
