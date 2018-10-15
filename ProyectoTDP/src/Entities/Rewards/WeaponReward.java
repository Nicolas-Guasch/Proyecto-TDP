package Entities.Rewards;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.GameObject;
import Engine.Vector2;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;
import Entities.Weapons.Weapon;
import GenericVisitor.MonoVisitor;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.AlwaysLateral;
import Scripts.Directionable;

public class WeaponReward extends Reward<WeaponReward>
{

    private Weapon weapon;
    //private SpriteRenderer renderer = new SpriteRenderer(new SpriteData(Paths.MonedaArma, new Vector2(400,400)));
    private Renderizable renderer;

    public WeaponReward(GameObject referenced, SpriteData sprite)
    {
        super(referenced);
        renderer = new Renderizable(sprite);
        referenced.setRenderer(renderer);

        var hitBox = HitBox.getOne(100,100,this);
        HitBoxesManager.getInstance().addHitBox(hitBox,HitBoxesManager.REWARDS);
        referenced.addHitBox(hitBox);
        referenced.addComponent(new AlwaysLateral(new Vector2(0,-5)));
        renderer.show();
    }

    @Override
    public void collideWith(PlayerShip ent)
    {
        if(weapon!=null)
        {
            ent.addWeapon(weapon);
            weapon = null;
            Destroy();
        }
    }

    @Override
    public void collideWith(EnemyShip ent) {

    }


    public void setWeapon(Weapon wea) {
        weapon = wea;
    }

    @Override
    public void accept(MonoVisitor<WeaponReward> weaponRewardVisitor) {
        weaponRewardVisitor.visit(this);
    }
}
