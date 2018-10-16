package Entities;

import Engine.Component;
import Collisions.CollisionData;
import Engine.GameObject;
import Entities.Rewards.Reward;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;
import GenericVisitor.MonoVisitor;

public class BarricadeEnem extends Entity<BarricadeEnem> {

	public BarricadeEnem(GameObject referenced) {
		super(referenced);
	}

	public void addBehaviour(Component comp) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	public void removeBehaviour(Component comp) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
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

	}

	public void collideWith(Reward ent) {

	}

	@Override
	public void reportCollision(CollisionData data)
	{
		data.Their().collideWith(this);
	}

	@Override
	public void accept(MonoVisitor<BarricadeEnem> obstacleMonoDirectionalVisitor) {
		obstacleMonoDirectionalVisitor.visit(this);
	}
}
