package Entities.Builders.Directors;

import Entities.Builders.ObstacleMonoDirectionalBuilder;
import Entities.BarricadeEnem;

public class ObstacleMonoDirectionalDirector
{
    private ObstacleMonoDirectionalBuilder builder;

    public void setBuilder(ObstacleMonoDirectionalBuilder builder){
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

    public BarricadeEnem get(){
        return builder.get();
    }
}