package Engine.Components;

import java.awt.*;
import java.util.Vector;

import Engine.EngineGetter;
import Engine.Vector2;
import ParaTestear.SonRenderer;
import RenderingSystem.SpriteData;
import Entities.Entity;
import RenderingSystem.SpriteRenderer;
import RenderingSystem.Window;

import javax.swing.*;


public class RectangleCollider extends AbstractCollider<RectangleCollider>
{
    private Vector2 dimensions;
    private Transform transform;
    private SpriteRenderer outline;
    private float ratio;

    public RectangleCollider(Vector2 dimensions, Entity entity)
    {
        super(entity);
        this.ratio = dimensions.length()/2;

    }
    public RectangleCollider(SpriteData spriteData, Entity entity)
    {
        this(new Vector2(spriteData.getWidth(),spriteData.getHeight()),entity);
    }

    @Override
    public void Start() {
        transform = getEntity().getReferenced().getTransform();
    }


    public float diagonalLength(){
        return dimensions.length();
    }

    public float distToCenter(Vector2 v){
        return transform.position().distanceTo(v);
    }

    public CollisionData CheckCollision(RectangleCollider c )
    {
        if(c.transform().position().distanceTo(transform().position()) < c.ratio + ratio)
        {
            return new CollisionData(entity,c.entity,c.transform().position().sum(transform.position()).prod(.5f));
        }
        return null;
    }


}
