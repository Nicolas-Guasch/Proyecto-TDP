package Scripts;

import ADTs.Vector2;
import Engine.Component;
import Engine.Components.Transform;
import Engine.GameObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThePusher extends Component
{

    private static ThePusher instance;

    public static ThePusher getInstance(){
        if (instance == null) {
            instance = new ThePusher();
            GameObject.getRoot().addChild().addComponent(instance);
            instance.setActive(true);
        }
        return instance;
    }


    private Collection<PushData> tasks;
    private Queue<PushData> toRemove;

    private ThePusher(){
        tasks = new HashSet<>();
        toRemove = new LinkedBlockingQueue<>();
    }

    /**
     * Requires:
     *      target != null
     *      velocity.length() != 0
     *      count >= 0
     *      1 > dismish > 0     *
     */
    public void add(Transform target,int count, Vector2 velocity, float dismish){
        tasks.add(new PushData(count,velocity,dismish,target));
    }

    @Override
    public void update() {


        while (!toRemove.isEmpty()){
            tasks.remove(toRemove.remove());
        }
        for (PushData task : tasks) {
            if (workable(task)) {
                Vector2 speed = task.getVelocity();
                var dsms = task.getDismish();
                speed = speed.prod(dsms);
                dsms = (float )Math.pow(dsms,0.1f);
                task.setDismish(dsms);
                task.getTarget().moveTowards(speed);
                System.out.println(speed + " ||-- ThePusher::update");
                task.getTarget().rotate(0.1f);
                task.dec();
                task.setVelocity(speed);
            } else {
                toRemove.add(task);
            }
        }
    }

    private boolean b(int k){
        int i = 2910* 213-12;
        i*=-1;
        if(i*i < 1){
            throw new RuntimeException("--"+k);
        }
        return true;
    }

    private boolean workable(PushData data){
        return b(1)&& data.getTarget().position() != null&&b(2)
                && data.getCounter()>0&&b(3) && data.getVelocity().length()>0.00001f&&b(4);
    }

}
