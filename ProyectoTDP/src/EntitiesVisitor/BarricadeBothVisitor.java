package EntitiesVisitor;

import ADTs.IVector2;
import Engine.Components.ITransform;
import Entities.EnemyBullet;
import Entities.PlayerBullet;

public class BarricadeBothVisitor extends VisitorEntity
{

    private final ITransform ITransform;

    public BarricadeBothVisitor(ITransform ITransform){
        this.ITransform = ITransform;
    }

    private IVector2 getRepulsion(IVector2 repulsor, IVector2 repulsado, IVector2 repulsor_top, IVector2 repulsado_top)
    {
        float angle = repulsado_top.getUnaryAngle(repulsor_top);
        IVector2 dir = repulsado_top.rotateUnary(-angle);
        //Vector2 dir = repulsor_top.rotateUnary(-angle);
        return dir;
    }

    private void bounce(ITransform quiet, ITransform traveller){
        IVector2 nextTop = getRepulsion(quiet.position(),traveller.position(),quiet.top(),traveller.top());
        traveller.moveTowards(nextTop.withLength(5));
        traveller.setTop(nextTop);
    }

    @Override
    public void visit(PlayerBullet bullet) {
        bounce(ITransform,bullet.transform());
    }

    @Override
    public void visit(EnemyBullet bullet) {
        bounce(ITransform,bullet.transform());
    }
}
