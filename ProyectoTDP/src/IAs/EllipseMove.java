package IAs;

import Engine.Vector2;
import Entities.Entity;

public class EllipseMove extends ParametricMove {

    public EllipseMove(EntityQuery decorated) {
        super(decorated);
    }

    @Override
    public Vector2 whereToMove(Entity ent, int t) {
        return null;
    }


}
