package Scripts;

import ADTs.IVector2;
import ADTs.Vector2;
import Audio.SoundManager;
import Observer.IBroadcaster;
import Observer.Invoker;
import Observer.ObserverPack;
import Observer.ObserverSystem;
import Engine.Component;
import Engine.Components.Transform;

import java.util.Iterator;

public class Jumper extends Component {

    private final Transform transform;
    private int delay;
    private Iterator<IVector2> points;
    private IBroadcaster<IVector2> onComplete;
    private Invoker<IVector2> invokerComplete;

    private IVector2 firstPos;

    public Jumper(Iterable<IVector2> path, Transform tr, int delayFrames) {
        points = path.iterator();
        this.transform = tr;
        this.delay = delayFrames;
        this.firstPos = tr.position();
        ObserverPack<IVector2> v = ObserverSystem.getInstance().getBroadcaster();
        onComplete = v.Broadcaster;
        invokerComplete = v.Invoker;
    }


    @Override
    public void update() {
        delay--;
        if(delay>0){
            transform.setPosition(firstPos);
            return;
        }
        if(!points.hasNext()){
            SoundManager.Instance().TieDowns(transform.position().withLength(50));
            setActive(false);
            gameObject().destroy();
            invokerComplete.invoke(transform.position());
            return;
        }
        transform.setPosition(points.next());
    }

    public IBroadcaster<IVector2> getOnComplete() {
        return onComplete;
    }
}
