package GenericVisitor;

import Engine.Components.Transform;
import Engine.EngineGetter;
import Entities.Entity;
import Entities.Ships.EnemyShip;
import Entities.Ships.ShipBoss;
import EntitiesVisitor.VisitorEntity;

public class FreezeVisitor extends VisitorEntity
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
            ship.getBagPack().setActive(false);
            EngineGetter.Instance().get().waitForFrames(() -> reactive(ship), (int)time*60);
        }
    }
    public void visit(ShipBoss ship)
    {
        if(precondition(ship))
        {
            ship.getPilot().setActive(false);
            ship.getBagPack().setActive(false);
            EngineGetter.Instance().get().waitForFrames(() -> reactive(ship), (int)time*60/2);
        }
    }

    private boolean precondition(Entity ship)
    {
        return ship.referenced().transform().position().distanceTo(center.position()) < ratio;
    }

    private void reactive(EnemyShip ship)
    {
        ship.getPilot().setActive(true);
        ship.getBagPack().setActive(true);
    }
    private void reactive(ShipBoss ship)
    {
        ship.getPilot().setActive(true);
        ship.getBagPack().setActive(true);
    }


}
