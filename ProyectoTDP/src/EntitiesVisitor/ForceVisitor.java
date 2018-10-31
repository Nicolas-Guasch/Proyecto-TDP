package EntitiesVisitor;

import Entities.Entity;
import Entities.Ships.Player.PlayerShip;
import SpecialPowers.TheForcePower;

public class ForceVisitor extends VisitorEntity
{

    private Entity ent;

    public void setEntity(Entity entity) {
        this.ent = entity;
    }

    @Override
    public void visit(PlayerShip playerShip) {
        if(ent !=null){

            playerShip.addSpecial(new TheForcePower());
            ent.data().setHealth(-1);
        }
        ent=null;
    }
}
