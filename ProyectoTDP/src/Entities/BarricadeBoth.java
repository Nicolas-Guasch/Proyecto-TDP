package Entities;

import Engine.Component;
import Collisions.CollisionData;
import Engine.GameObject;
import Entities.Rewards.Reward;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;
import GenericVisitor.MonoVisitor;

public class BarricadeBoth extends Entity<BarricadeBoth> {

	public BarricadeBoth(GameObject referenced) {
		super(referenced);
	}



	public void collideWith(PlayerShip ent) {

	}


	public void collideWith(EnemyShip ent) {

	}

	public void collideWith(BarricadeBoth ent) {

	}

	public void collideWith(BarricadeEnem ent) {

	}

	public void collideWith(PlayerBullet ent) {
		data.decHealth(ent.data().getDamage());
	}

	public void collideWith(EnemyBullet ent) {
		data.decHealth(ent.data().getDamage());
	}
	public void collideWith(Reward ent)  {

	}

	public void reportCollision(CollisionData data)
	{
		data.Their().collideWith(this);
	}

	@Override
	public void accept(MonoVisitor<BarricadeBoth> obstacleBidirectionalVisitor) {
		obstacleBidirectionalVisitor.visit(this);
	}
}
