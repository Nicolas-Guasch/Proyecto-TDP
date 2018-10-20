package EntitiesVisitor;

import Entities.BarricadeBoth;
import Entities.BarricadeEnem;
import Entities.Entity;
import Entities.Ships.EnemyShip;

import java.util.Collection;
import java.util.Collections;

public class GetEnemiesAndBarricades extends VisitorEntity {

    private final Collection<Entity> col;

    public GetEnemiesAndBarricades(Collection<Entity> col){
        this.col = col;
    }

    @Override
    public void visit(EnemyShip enemyShip) {
        col.add(enemyShip);
    }

    @Override
    public void visit(BarricadeBoth barricade) {
        col.add(barricade);
    }

    @Override
    public void visit(BarricadeEnem barricade) {
        col.add(barricade);
    }
}
