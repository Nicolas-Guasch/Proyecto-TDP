package Entities;

import Engine.Components.CollisionData;
import Engine.GameObject;
import Entities.Weapons.EnemyBagpack;
import Entities.Weapons.WeaponSet;

public class EnemyShip extends Ship {



	public EnemyShip(GameObject referenced)
	{
		super(referenced,new EnemyBagpack());
	}

	public void collideWith(PlayerShip ent) {
		data.setHealth(data.getHealth()-ent.getData().getDamage());
	}

	public void collideWith(EnemyShip ent) {

	}

	public void collideWith(ObstacleBidirectional ent) {

	}

	public void collideWith(ObstacleMonoDirectional ent) {

	}

	public void collideWith(PlayerBullet ent) {
		data.setHealth(data.getHealth()-ent.getData().getDamage());
	}

	public void collideWith(EnemyBullet ent) {

	}


	public void collideWith(Reward ent) {

	}

	@Override
	public void reportCollision(CollisionData data)
	{
		data.Their().collideWith(this);
	}

	public WeaponSet getBagpack() {
		return weapons;
	}
}
