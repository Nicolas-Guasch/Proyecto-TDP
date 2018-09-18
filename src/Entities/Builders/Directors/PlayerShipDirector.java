package Entities.Builders.Directors;

import Entities.Builders.PlayerShipBuilder;
import Entities.PlayerShip;

public class PlayerShipDirector
{
    private PlayerShipBuilder builder;

    public void setBuilder(PlayerShipBuilder builder){
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

    public PlayerShip get(){
        return builder.get();
    }
}