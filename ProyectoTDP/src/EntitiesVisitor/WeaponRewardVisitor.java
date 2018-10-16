package EntitiesVisitor;

import Entities.Rewards.Reward;
import Entities.Ships.PlayerShip;
import Entities.Weapons.Weapon;

public class WeaponRewardVisitor extends VisitorEntity
{
    private Weapon weapon;
    private final Reward related;

    public WeaponRewardVisitor(Weapon weapon, Reward related)
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
            related.Destroy();
        }

    }
}
