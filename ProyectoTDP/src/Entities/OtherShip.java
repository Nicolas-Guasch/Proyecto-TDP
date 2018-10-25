package Entities;

import Engine.GameObject;
import Entities.Ships.Ship;
import Entities.Weapons.Arsenal;
import EntitiesVisitor.VisitorEntity;

public class OtherShip extends Ship
{

    public OtherShip(GameObject referenced, Arsenal weapons) {
        super(referenced, weapons);
        data = EntityData.WithEqualsValues(1);
    }

    @Override
    public void accept(VisitorEntity visitor) {
        visitor.visit(this);
    }
}
