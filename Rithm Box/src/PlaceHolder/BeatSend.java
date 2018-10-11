package PlaceHolder;

import Observer.Broadcaster;

public class BeatSend<Signal> extends Broadcaster<Signal> implements Runnable
{

    private int bpm;

    private synchronized int secsbettween(){
        return (250 * 60)/(bpm);
    }
    private final Signal signal;
    private static volatile boolean paused = true;

    BeatSend(int bpm, Signal signal)
    {
        this.bpm = bpm;
        this.signal = signal;
    }

    public synchronized void playpause()
    {
        paused = !paused;
    }

    @Override
    public void run() {

        try {
            do {
                if(paused)continue;
                broadcast(signal);
                Thread.sleep(secsbettween());
            } while (true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setBpm(int nextbpm) {
        bpm = nextbpm;
    }
}
