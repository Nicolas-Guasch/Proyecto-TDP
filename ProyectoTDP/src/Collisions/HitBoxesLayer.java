package Collisions;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class HitBoxesLayer implements Iterable<HitBox>
{
    private Collection<HitBox> hitBoxes;

    HitBoxesLayer()
    {
        hitBoxes = new LinkedList<>();
    }

    public void addHitBox(HitBox hitBox)
    {
        hitBoxes.add(hitBox);
    }

    public void removeHitBox(HitBox hitBox)
    {
        hitBoxes.remove(hitBox);
    }

    public Iterator<HitBox> iterator()
    {
        return hitBoxes.iterator();
    }

    public void checkLayer(Iterable<HitBox> other)
    {
        for (var mine : this) for (var their : other)
        if(condition(mine,their))
        {
            var data = mine.checkCollision(their);
            if(data!=null)
            {
                mine.getEntity().reportCollision(data);
            }
        }
    }
    public boolean contains(HitBox hitBox) {
        return hitBoxes.contains(hitBox);
    }

    private boolean condition(HitBox mine, HitBox their) {
        return mine!=null && their!=null &&their.getEntity()!=null && mine.getEntity()!=null&& mine.isActive() && their.isActive();
    }

}
