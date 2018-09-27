package Entities.Rewards;

import Engine.Components.CircleCollider;
import Engine.GameObject;
import Engine.Vector2;
import Entities.EnemyShip;
import Entities.PlayerShip;
import Entities.Weapons.Weapon;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.Directionable;

public class WeaponReward extends Reward
{

    private Weapon weapon;
    //private SpriteRenderer renderer = new SpriteRenderer(new SpriteData(Paths.MonedaArma, new Vector2(400,400)));
    private Renderizable renderer;

    public WeaponReward(GameObject referenced)
    {
        super(referenced);
        var sd = new SpriteData("MonedaArma", new Vector2(400,400));
        renderer = new Renderizable(sd);
        referenced.setRenderer(renderer);
        referenced.addCollider(new CircleCollider(100,this));
        referenced.addComponent(new Directionable(new Vector2(0,-5)));
        renderer.Show();
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
}
