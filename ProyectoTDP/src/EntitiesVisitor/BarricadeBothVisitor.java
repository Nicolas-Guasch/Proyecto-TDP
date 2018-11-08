package EntitiesVisitor;

import ADTs.Vector2;
import Engine.Components.Transform;
import Entities.Bullet;
import Entities.EnemyBullet;
import Entities.PlayerBullet;
import Entities.Ships.ShipBoss;

public class BarricadeBothVisitor extends VisitorEntity
{

    private final Transform transform;

    public BarricadeBothVisitor(Transform transform){
        this.transform = transform;
    }

    private Vector2 getRepulsion(Vector2 repulsor, Vector2 repulsado, Vector2 repulsor_top, Vector2 repulsado_top)
    {
        var angle = repulsado_top.getUnaryAngle(repulsor_top);
        var dir = repulsado_top.rotateUnary(-angle);
        //var dir = repulsor_top.rotateUnary(-angle);
        return dir;
    }

    private void bounce(Transform quiet, Transform traveller){
        var nextTop = getRepulsion(quiet.position(),traveller.position(),quiet.top(),traveller.top());
        traveller.moveTowards(nextTop.withLength(5));
        traveller.setTop(nextTop);
    }

    @Override
    public void visit(PlayerBullet bullet) {
        bounce(transform,bullet.transform());
    }

    @Override
    public void visit(EnemyBullet bullet) {
        bounce(transform,bullet.transform());
    }
}
