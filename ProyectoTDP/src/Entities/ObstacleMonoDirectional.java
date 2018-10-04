package Entities;

import Engine.Component;
import Collisions.CollisionData;
import Engine.GameObject;
import Entities.Rewards.Reward;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;
import GenericVisitor.Visitor;

public class ObstacleMonoDirectional extends Entity<ObstacleMonoDirectional> {

	public ObstacleMonoDirectional(GameObject referenced) {
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

	public void collideWith(ObstacleBidirectional ent) {

	}

	public void collideWith(ObstacleMonoDirectional ent) {

	}

	public void collideWith(PlayerBullet ent) {

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
	public void accept(Visitor<ObstacleMonoDirectional> obstacleMonoDirectionalVisitor) {
		obstacleMonoDirectionalVisitor.visit(this);
	}
}
