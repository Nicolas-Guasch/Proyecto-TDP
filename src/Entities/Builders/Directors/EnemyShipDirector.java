package Entities.Builders.Directors;

import Entities.Builders.EnemyShipBuilder;
import Entities.EnemyShip;

public class EnemyShipDirector
{
    private EnemyShipBuilder builder;

    public void setBuilder(EnemyShipBuilder builder){
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

    public EnemyShip get(){
        return builder.get();
    }
}