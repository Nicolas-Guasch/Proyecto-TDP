package Rewards;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.GameObject;
import ADTs.Vector2;
import Entities.Entity;
import Entities.Weapons.Weapon;
import EntitiesVisitor.VisitorEntity;
import EntitiesVisitor.WeaponRewardVisitor;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.AlwaysLateral;

public class WeaponReward extends Entity
{

    private Weapon weapon;
    private Renderizable renderer;

    WeaponReward(GameObject referenced, SpriteData sprite)
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

    public void setWeapon(Weapon wea) {
        weapon = wea;
        visitor = new WeaponRewardVisitor(weapon,this);
    }

    @Override
    public void accept(VisitorEntity visitor) {
        visitor.visit(this);
    }
}
