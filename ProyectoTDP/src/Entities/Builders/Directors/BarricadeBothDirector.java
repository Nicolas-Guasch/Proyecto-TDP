package Entities.Builders.Directors;

import Entities.Builders.BarricadeBothBuilder;
import Entities.BarricadeBoth;

public class BarricadeBothDirector
{
    private BarricadeBothBuilder builder;

    public void setBuilder(BarricadeBothBuilder builder){
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

    public BarricadeBoth get(){
        return builder.get();
    }
}
