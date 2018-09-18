package Entities.Builders.Directors;

import Entities.Builders.ObstacleBidirectionalBuilder;
import Entities.ObstacleBidirectional;

public class ObstacleBidirectionalDirector
{
    private ObstacleBidirectionalBuilder builder;

    public void setBuilder(ObstacleBidirectionalBuilder builder){
        this.builder = builder;
    }

    public void create(){
        builder.create();
    }

    public void assemble(){
        builder.assembleSprite();
        builder.assembleCollider();
        builder.assembleBehaviours();
        builder.assembleData();
    }

    public ObstacleBidirectional get(){
        return builder.get();
    }
}
