package EntitiesVisitor;

import Entities.Ships.EnemyShip;

import java.util.Collection;
import java.util.Collections;

public class GetEnemies extends VisitorEntity {

    private final Collection<EnemyShip> collector;

    public GetEnemies(Collection<EnemyShip> collector) {
        super();
        this.collector = collector;
    }

    @Override
    public void visit(EnemyShip enemyShip) {
        collector.add(enemyShip);
    }
}
