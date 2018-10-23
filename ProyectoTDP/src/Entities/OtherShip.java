package Entities;

import Engine.GameObject;
import Entities.Ships.Ship;
import Entities.Weapons.WeaponSet;
import EntitiesVisitor.VisitorEntity;
import GameData.GameSettings;

public class OtherShip extends Ship
{

    public OtherShip(GameObject referenced, WeaponSet weapons) {
        super(referenced, weapons);
        data = EntityData.WithEqualsValues(1);
    }

    @Override
    public void accept(VisitorEntity visitor) {
        visitor.visit(this);
    }
}
