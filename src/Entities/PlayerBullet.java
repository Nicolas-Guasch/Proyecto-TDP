package Entities;

import Engine.Components.CollisionData;
import Engine.GameObject;

public class PlayerBullet extends Bullet {

	public PlayerBullet(GameObject referenced) {
		super(referenced);
	}


	public void collideWith(PlayerShip ent) {

	}

	public void collideWith(EnemyShip ent) {
		data.setHealth(0);
	}

	public void collideWith(ObstacleBidirectional ent) {
		data.setHealth(0);
	}


	public void collideWith(ObstacleMonoDirectional ent) {
		data.setHealth(0);
	}


	public void collideWith(PlayerBullet ent) {

	}


	public void collideWith(EnemyBullet ent) {

	}


	public void collideWith(Reward ent) {

	}

	@Override
	public void reportCollision(CollisionData data) {
		data.Their().collideWith(this);
	}
}
