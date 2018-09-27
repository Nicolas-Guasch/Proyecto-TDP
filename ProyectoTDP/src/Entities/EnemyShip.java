package Entities;

import Collisions.CollisionData;
import Engine.GameObject;
import Entities.Rewards.Reward;
import Entities.Weapons.EnemyBagpack;
import Entities.Weapons.WeaponSet;
import GameData.CurrentMatchData;

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

	@Override
	public void onDeath() {
		super.onDeath();
		CurrentMatchData.getMatchData().incScore(1);
	}
}
