package Misc;

import ADTs.Vector2;
import Engine.Component;
import Tools.Random;

public class StarBeh extends Component
{
    private int i,max;
    StarBeh(int seed){
        i=-900;
        max = Random.value(seed*8,seed*seed*9);
    }

    @Override
    public void update() {
        i++;
        if(i>=max){
            i=0;
            transform().moveTowards(Vector2.Random(Random.value(0,300)));
        }

    }
}
