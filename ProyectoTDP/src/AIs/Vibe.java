package AIs;

import Engine.Component;

/**
 * Behaviour which implements lateral a vibration movement
 * respect the front of the object
 */
public class Vibe extends Component
{

    private final int freq;
    private float amp;

    private int i = 0;
    public Vibe(int freq, float amp)
    {
        this.freq = freq;
        this.amp = amp;
    }

    @Override
    public void update() {
        if(i>=freq)
        {
            i=0;
            amp *= -1;
        }
        var right = transform().top().right(amp);
        var pos = transform().position();

        pos = pos.sum(right);

        transform().setPosition(pos);

        i++;
    }
}
