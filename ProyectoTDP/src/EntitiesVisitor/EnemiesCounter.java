package EntitiesVisitor;

import Entities.Ships.EnemyShip;

public class EnemiesCounter extends VisitorEntity
{
    private int count;

    public EnemiesCounter(){
        count = 0;
    }

    @Override
    public void visit(EnemyShip enemyShip) {
        if(enemyShip.alive()) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
