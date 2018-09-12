package Entities.Builder;

import Exceptions.BuilderExcption;
import Entities.Ship;

public class ShipIndustry
{
    private ShipBuilder builder;

    public void setBuilder (ShipBuilder builder)
    {
        this.builder = builder;
    }
    public Ship getShip()
    {
        if(builder.getShip()==null)
        {
            throw new BuilderExcption("Before obtaining a ship, you must first complete the assembly process");
        }
        return builder.getShip();
    }

    public void assembleShip()
    {
        builder.createShip();
        builder.createWeapon();
        builder.createCollider();
        builder.createController();
        builder.createRenderer();
        builder.setData();
    }
}
