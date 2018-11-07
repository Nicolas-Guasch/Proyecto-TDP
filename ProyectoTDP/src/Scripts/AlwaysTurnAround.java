package Scripts;

import ADTs.Vector2;
import Engine.Component;
import Engine.Components.Transform;

public class AlwaysTurnAround extends Component
{
    private final float angularSpeed;
    private final Transform center;
    private Vector2 currentGap;

    public AlwaysTurnAround(Transform center, float ratio, float angularSpeed, Vector2 firstSide){
        this.center=center;
        this.angularSpeed=angularSpeed;
        this.currentGap = firstSide.withLength(ratio);
    }

    @Override
    public void update() {
        currentGap = currentGap.rot(angularSpeed);
        transform().setPosition(center.position().sum(currentGap));
        transform().setTop(currentGap);
    }
}
