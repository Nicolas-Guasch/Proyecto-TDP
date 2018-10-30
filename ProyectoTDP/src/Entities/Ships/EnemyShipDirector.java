package Entities.Ships;

import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.EnemyShip;

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
        builder.assembleHitBox();
        builder.assembleBehaviours();
        builder.assembleWeapons();
        builder.assembleData();
    }

    public BaseEnemyShip get(){
        return builder.get();
    }
}