package Entities.Builders.Directors;


import Entities.Builders.IBullet;
import Entities.Builders.IBulletBuilder;

public class BulletDirector<BulletType extends IBullet,BuilderType extends IBulletBuilder<BulletType>> implements IBulletDirector<BulletType,BuilderType>
{
    private BuilderType builder;

    public void setBuilder(BuilderType builder){
        this.builder = builder;
    }

    public void create(){
        builder.create();
    }

    public void assemble(){
        builder.assembleSprite();
        builder.assembleHitBox();
        builder.assembleBehaviours();
        builder.assembleData();
    }

    public BulletType get(){
        return builder.get();
    }
}
