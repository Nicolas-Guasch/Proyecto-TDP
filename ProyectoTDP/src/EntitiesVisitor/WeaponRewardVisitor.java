package EntitiesVisitor;


import Entities.Entity;
import Entities.Ships.Player.PlayerShip;
import Entities.Weapons.Weapon;

public class WeaponRewardVisitor extends VisitorEntity
{
    private Weapon weapon;
    private final Entity related;

    public WeaponRewardVisitor(Weapon weapon, Entity related)
    {
        assert related!=null;
        assert weapon != null;
        this.weapon = weapon;
        this.related = related;
    }

    @Override
    public void visit(PlayerShip ent)
    {
        if(weapon!=null)
        {
            ent.addWeapon(weapon);
            weapon = null;
            related.data().setHealth(-1);
        }

    }
}
