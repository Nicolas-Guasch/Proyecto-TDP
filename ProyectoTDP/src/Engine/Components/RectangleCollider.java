package Engine.Components;

import Engine.EngineGetter;
import Engine.Vector2;
import Entities.Entity;

import RenderingSystem.SpriteData;
import RenderingSystem.SpriteRenderer;
import RenderingSystem.Window;

import java.util.Vector;

public class RectangleCollider extends AbstractCollider<RectangleCollider>
{
    private Vector2 dimensions;
    private Transform transform;
    private SpriteRenderer outline;

    public RectangleCollider(Vector2 dimensions, Entity entity)
    {
        super(entity);
        this.dimensions = dimensions;

    }
    public RectangleCollider(SpriteData spriteData, Entity entity)
    {
        this(new Vector2(spriteData.getWidth(),spriteData.getHeight()),entity);
    }

    @Override
    public void Start() {
        transform = gameObject().getTransform();
        //System.out.println(toString());
    }

    boolean actualized = false;
    public void Update()
    {
        if(!actualized && gameObject().getRenderer()!=null)
        {
            actualized = true;
            //gameObject().getRenderer().Sprite().setBorder(BorderFactory.createLineBorder(Color.green));

        }


    }


    private Vector2 bottomLeft()
    { //pos+dim/2

        return transform.position().minus(bottomSide().div(2)).minus(leftSide().div(2));
    }
    private Vector2 topRight()
    {//pos-dim/2

        return transform.position().sum(bottomSide().div(2)).sum(leftSide().div(2));
    }

    private Vector2 bottomSide(){
        return transform.top(dimensions.x()).right();
    }

    private Vector2 leftSide(){
        return transform.top(dimensions.y());
    }

    public Iterable<Vector2> vertices(){
        Vector<Vector2> res = new Vector<>();

        Vector2 dy = transform.top(dimensions.y()).div(2),
                dx = transform.top(dimensions.x()).right().div(2);
        int dir[] = {-1,1};
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                Vector2 diag = dx.prod(dir[i]).sum(dy.prod(dir[j]));
                res.add(transform.position().sum(diag));
            }
        }
        return res;
    }

    public float diagonalLength(){
        return dimensions.length();
    }

    public float distToCenter(Vector2 v){
        return transform.position().distanceTo(v);
    }

    public CollisionData CheckCollision(RectangleCollider c )
    {
        CollisionData data = null;
        if(c.distToCenter(transform.position())<=dimensions.length()+c.diagonalLength()) {
            Vector2 bottom = bottomSide();
            Vector2 left = leftSide();
            Vector2 vert = bottomLeft();
            for (Vector2 pto : c.vertices()) {
                Vector2 vertToPto = pto.minus(vert);
                float prodBot = bottom.scalarProd(vertToPto);
                float prodLeft = left.scalarProd(vertToPto);
                if (0 <= prodBot && prodBot <= bottom.lengthSq() && 0 <= prodLeft && prodLeft <= left.lengthSq())// si colisiona:
                {
                    data = new CollisionData(entity, c.entity, pto);
                    break;
                }
            }
            //if(data==null)data = c.CheckCollision(this);
        }
        return data;
    }

    public String toString(){
        String res="";
        res+="Position: "+transform.position().toString()+"\n";
        res+="Dimensions: "+dimensions.toString()+"\n";
        res+="Vertices: ";
        for(Vector2 v:vertices())res+=" "+v.toString();
        res+="\nBottomLeft: "+bottomLeft().toString()+" "+"\n";
        res+="TopLeft: "+ (bottomLeft().sum(leftSide())).toString() +"\n";
        res+="BottomRight: "+ (bottomLeft().sum(bottomSide())).toString() +"\n";
        res+="TopRight: "+topRight()+"\n";
        return res;
    }
}
