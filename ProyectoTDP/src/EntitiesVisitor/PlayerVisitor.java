package EntitiesVisitor;

import Entities.EnemyBullet;
import Entities.Ships.EnemyShip;
import RenderingSystem.RenderingTools;

public class PlayerVisitor extends VisitorEntity
{
    @Override
    public void visit(EnemyShip ent) {
        ent.data().takeDamage(ent.data().getDamage());
        RenderingTools.doScreenShake(4.9f);
    }

    public void visit(EnemyBullet ent) {
        ent.data().takeDamage(ent.data().getDamage());
    }
}
