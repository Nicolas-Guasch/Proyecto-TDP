package Scripts;

import ADTs.IVector2;
import Audio.SoundManager;
import Engine.Components.ITransform;
import Observer.IBroadcaster;
import Observer.Invoker;
import Observer.ObserverPack;
import Observer.ObserverSystem;
import Engine.Component;

import java.util.Iterator;

public class Jumper extends Component {

    private final ITransform ITransform;
    private int delay;
    private Iterator<IVector2> points;
    private IBroadcaster<IVector2> onComplete;
    private Invoker<IVector2> invokerComplete;

    private IVector2 firstPos;

    public Jumper(Iterable<IVector2> path, ITransform tr, int delayFrames) {
        points = path.iterator();
        this.ITransform = tr;
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
            ITransform.setPosition(firstPos);
            return;
        }
        if(!points.hasNext()){
            SoundManager.Instance().TieDowns(ITransform.position().withLength(50));
            setActive(false);
            gameObject().destroy();
            invokerComplete.invoke(ITransform.position());
            return;
        }
        ITransform.setPosition(points.next());
    }

    public IBroadcaster<IVector2> getOnComplete() {
        return onComplete;
    }
}
