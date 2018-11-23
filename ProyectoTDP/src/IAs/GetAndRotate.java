package IAs;

import ADTs.IVector2;
import ADTs.Vector2;

public class GetAndRotate implements IDirGiver
{

    private final boolean turn;
    private int counter=0;
    private static final int max = 5000;
    private float sp = 1;
    private IVector2 last;
    private float rot, frot;
    GetAndRotate(float rot, IVector2 first, boolean rotex){
        last = first;
        this.rot = rot;
        frot = rot;
        this.turn = rotex;
    }


    @Override
    public IVector2 get() {
        IVector2 v = last.rotateUnary(rot/10);

        counter++;
        if(counter>max){
            counter = 0;
            rot = frot;
            if(turn){
                v = v.prod(-1);
            }
        }
        last = v;
        return v;
    }
}
