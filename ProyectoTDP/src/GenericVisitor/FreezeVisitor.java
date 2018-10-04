package GenericVisitor;

import Engine.Components.Transform;
import Engine.EngineGetter;
import Engine.Vector2;
import Entities.Ships.EnemyShip;

public class FreezeVisitor implements Visitor<EnemyShip>
{

    private final float time;
    private final Transform center;
    private final float ratio;

    public FreezeVisitor(float time, Transform center, float ratio)
    {
        this.time = time;
        this.center = center;
        this.ratio = ratio;
    }

    @Override
    public void visit(EnemyShip ship)
    {
        if(precondition(ship))
        {
            ship.getPilot().setActive(false);
            ship.getBagpack().setActive(false);
            EngineGetter.Instance().get().WaitForFrames(() -> reactive(ship), (int)time*60);
        }
    }

    private boolean precondition(EnemyShip ship)
    {
        return ship.getReferenced().getTransform().position().distanceTo(center.position()) < ratio;
    }

    private void reactive(EnemyShip ship)
    {
        ship.getPilot().setActive(true);
        ship.getBagpack().setActive(true);
    }


}
