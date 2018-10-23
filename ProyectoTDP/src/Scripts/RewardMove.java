package Scripts;

import ADTs.Vector2;
import Engine.Component;

import java.util.Random;

public class RewardMove extends Component
{

    private float t;
    private int random_inc;
    private int dir =1;
    public RewardMove(int seed){
        t =0;
        random_inc = Math.abs(new Random(seed).nextInt(3)) + 3;
        random_inc = random_inc/25;
    }

    @Override
    public void update() {
        if(new Random(random_inc).nextInt(20)<3){
            dir *= -1;
            t = random_inc;
        }
        t+=random_inc;
        Vector2 direction = new Vector2(t*dir*200,0); // derivo (sin(t),-2t)
        if(transform()!=null){
            transform().MoveTowards(direction.withMaxLength(20));
        }
        transform().MoveTowards(Vector2.DOWN(5));
    }
}
