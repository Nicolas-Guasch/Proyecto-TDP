package Scripts;

import ADTs.Vector2;
import Audio.SoundManager;
import Observer.IBroadcaster;
import Observer.Invoker;
import Observer.observerPack;
import Observer.ObserverSystem;
import Engine.Component;
import Engine.Components.Transform;

import java.util.Iterator;

public class Jumper extends Component {

    private final Transform transform;
    private int delay;
    private Iterator<Vector2> points;
    private IBroadcaster<Vector2> onComplete;
    private Invoker<Vector2> invokerComplete;

    private Vector2 firstPos;

    public Jumper(Iterable<Vector2> path, Transform tr, int delayFrames) {
        points = path.iterator();
        this.transform = tr;
        this.delay = delayFrames;
        this.firstPos = tr.position();
        observerPack<Vector2> v = ObserverSystem.getInstance().getBroadcaster();
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

    public IBroadcaster<Vector2> getOnComplete() {
        return onComplete;
    }
}
