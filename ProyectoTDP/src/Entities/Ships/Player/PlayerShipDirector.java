package Entities.Ships.Player;

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
        builder.assembleHitBox();
        builder.assembleBehaviours();
        builder.assembleWeapons();
        builder.assembleData();
    }

    public PlayerShip get(){
        return builder.get();
    }
}