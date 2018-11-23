package EntitiesVisitor;

import ADTs.IVector2;
import ADTs.Vector2;
import Engine.EngineGetter;
import Entities.BarricadeBoth;
import Entities.PlayerBullet;
import Entities.Ships.EnemyShip;
import Entities.Ships.Player.PlayerShip;

public class EnemyVisitor extends VisitorEntity {
    private final EnemyShip ship;


    public EnemyVisitor(EnemyShip ship) {
        this.ship  = ship;
    }


    @Override
    public void visit(PlayerShip playerShip)
    {
        IVector2 bouncePoint = playerShip.referenced().transform().position();
        EngineGetter.Instance().get().waitForFrames(() -> bounce(bouncePoint),2);
        EngineGetter.Instance().get().waitForFrames(() -> bounce(bouncePoint),3);
        EngineGetter.Instance().get().waitForFrames(() -> bounce(bouncePoint),4);
    }

    @Override
    public void visit(BarricadeBoth barricade) {

        barricade.data().setDamage(10);

        IVector2 bouncePoint = barricade.referenced().transform().position();
        EngineGetter.Instance().get().waitForFrames(() -> bounce(bouncePoint),2);
        EngineGetter.Instance().get().waitForFrames(() -> bounce(bouncePoint),3);

    }

    @Override
    public void visit(PlayerBullet bullet)
    {
        IVector2 bouncePoint = bullet.referenced().transform().position();
        EngineGetter.Instance().get().waitForFrames(() -> bounce(bouncePoint),2);
        EngineGetter.Instance().get().waitForFrames(() -> bounce(bouncePoint),3);
        EngineGetter.Instance().get().waitForFrames(() -> bounce(bouncePoint),4);
    }

    private void bounce(IVector2 bouncePoint)
    {
        if(ship==null || !ship.alive() || ship.referenced() == null) return;
        IVector2 direction = ship.referenced().transform().position().sub(bouncePoint);
        ship.referenced().transform().moveTowards(direction.norma());
    }



}
