package Entities.Builders.Directors;

import Entities.Builders.BarricadeEnemBuilder;
import Entities.BarricadeEnem;

public class BarricadeEnemDirector
{
    private BarricadeEnemBuilder builder;

    public void setBuilder(BarricadeEnemBuilder builder){
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