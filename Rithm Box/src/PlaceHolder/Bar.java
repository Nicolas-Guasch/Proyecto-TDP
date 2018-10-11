package PlaceHolder;

import Observer.Broadcaster;
import SoundSystem.Sound;

public class Bar extends Broadcaster<Boolean> implements Runnable
{

    private final int steps;
    private final boolean[] sequence;

    private long bpm;
    private long nanosbettween;

    private boolean running;
    private int index;


    public Bar(int bpm, boolean[] sequence)
    {
        running = false;
        index = 0;
        this.steps = sequence.length;
        this.sequence = sequence;
        assert pot2(steps);
        this.bpm = bpm;
        nanosbettween = bpm/(240L * 1_000_000_000L);
    }

    //te dice si es una potencia de 2
    private boolean pot2(int num)
    {
        return (num%2==0 && pot2(num/2))|| num ==1;
    }

    @Override
    public void run()
    {
        broadcast(sequence[index]);
        index++;
        index = index< steps?index:0;
        try {
            Thread.sleep(0, Math.toIntExact(nanosbettween));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
