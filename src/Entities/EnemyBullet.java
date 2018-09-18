package Entities;

import Engine.Components.CollisionData;
import Engine.GameObject;

public class EnemyBullet extends Bullet {

	public EnemyBullet(GameObject referenced) {
		super(referenced);
	}



	public void collideWith(PlayerShip ent) {
		data.setHealth(0);
	}


	public void collideWith(EnemyShip ent) {

	}


	public void collideWith(ObstacleBidirectional ent) {
		data.setHealth(0);
	}

	public void collideWith(ObstacleMonoDirectional ent) {
		// no tiene que pasar nada
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


}
