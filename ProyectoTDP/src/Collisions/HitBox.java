package Collisions;

import ADTs.IVector2;
import Engine.Component;
import Engine.Components.Transform;
import Engine.GameObject;
import ADTs.Vector2;
import Entities.Entity;

import java.util.Collection;
import java.util.Vector;

/**
 * Post Refactor
 */
public final class HitBox extends Component {
    private static final int NoCheckDistance = 500;
    private Entity entity;
    private IVector2 dimensions;
    private Transform transform;


    public static HitBox getOne(float w, float h, Entity entity) {
        return getOne(new Vector2(w,h),entity);
    }

    public static HitBox getOne(IVector2 dimensions, Entity entity)
    {
        return new HitBox(dimensions,entity);
    }

    private HitBox(IVector2 dimensions, Entity entity) {
        if(entity==null)
        {
            throw new RuntimeException("Entity null");
        }
        this.entity = entity;
        this.dimensions = dimensions;
        transform = entity.referenced().transform();
    }

    public Entity getEntity() {
        return entity;
    }

    //private GameObject obj() {
      //  return entity.referenced();
   // }
    CollisionData checkCollision(HitBox other) {
        if(isFar(other.transform) || other.distToCenter(transform.position()) > dimensions.length() + other.diagonalLength())
            return null;

        IVector2 CollisionPoint = vertexInside(other);
        if(CollisionPoint == null)CollisionPoint = other.vertexInside(this);
        if(CollisionPoint != null)return new CollisionData(entity, other.entity, CollisionPoint);
        return null;
    }

    private Iterable<IVector2> vertices() {
        Collection<IVector2> res = new Vector<IVector2>();
        IVector2 dy = transform.top(dimensions.y()).half();
        IVector2 dx = transform.top(dimensions.x()).right().half();
        int dir[] = {-1, 1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                IVector2 diag = dx.prod(dir[i]).sum(dy.prod(dir[j]));
                res.add(transform.position().sum(diag));
            }
        }
        return res;
    }

    private float diagonalLength() {
        return dimensions.length();
    }

    private float distToCenter(IVector2 v) {
        return transform.position().distanceTo(v);
    }

    private boolean isFar(Transform other){
        boolean b1 = Math.abs(other.position().x()-transform.position().x()) > NoCheckDistance;
        boolean b2 = Math.abs(other.position().y()-transform.position().y()) > NoCheckDistance;
        return b1 || b2 ;
        // si la pifea mucho probar cambiar por un && o agrandar la distancia
    }


    private IVector2 vertexInside(HitBox other){
        IVector2 bottom = bottomSide();
        IVector2 left = leftSide();
        IVector2 vertex = bottomLeft();
        for (IVector2 pto : other.vertices()) {
            IVector2 vertToPto = pto.sub(vertex);
            float prodBot = bottom.scalarProd(vertToPto);
            float prodLeft = left.scalarProd(vertToPto);
            if (0 <= prodBot && prodBot <= bottom.lengthSq() && 0 <= prodLeft && prodLeft <= left.lengthSq())// si colisiona:
            {
                return pto;
            }
        }
        return null;
    }


    private IVector2 bottomLeft() {
        return transform.position().sub(bottomSide().div(2)).sub(leftSide().div(2));
    }

    private IVector2 topRight() {
        return transform.position().sum(bottomSide().div(2)).sum(leftSide().div(2));
    }

    private IVector2 bottomSide() {
        return transform.top(dimensions.x()).right();
    }

    private IVector2 leftSide() {
        return transform.top(dimensions.y());
    }


}
