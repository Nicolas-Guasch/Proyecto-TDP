package IAs;

import ADTs.Vector2;
import Entities.Entity;

public class EllipseMove extends ParametricMove {

    private final float b;
    private final float a;

    public EllipseMove(EntityQuery decorated, float a, float b) {
        super(decorated);
        this.a = a;
        this.b = b;
    }

    @Override
    public Vector2 whereToMove(Entity ent, int t) {
        return new Vector2(Math.cos(t/35f)*b,Math.sin(t/35f)*a).norma();
    }


}
