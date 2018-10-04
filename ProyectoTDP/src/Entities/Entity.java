package Entities;

import Collisions.HitBox;
import Engine.Component;
import Collisions.CollisionData;
import Engine.GameObject;
import Entities.Rewards.Reward;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;
import RenderingSystem.Renderizable;

public abstract class Entity {

	private GameObject referenced;
	protected EntityData data; // tiene setter y getter, pero la hago protected por comodidad



	public void onDeath(){}

	protected Entity(GameObject referenced)
	{
		this.referenced = referenced;
	}

	public void setData(EntityData data)
	{
		this.data = data;
	}

	public EntityData getData()
	{
		return data;
	}

	public void setRenderer(Renderizable rend)
	{
		referenced.setRenderer(rend);
	}

	//Collider must be attatched to this Entity and to layer
	public void setHitBox(HitBox hitBox)
	{
		referenced.addHitBox(hitBox);
	}

	public void addBehaviour(Component comp) {
		referenced.addComponent(comp);
	}

	public void removeBehaviour(Component comp) {
		referenced.removeComponent(comp);
	}
	public GameObject getReferenced() {
		return referenced;
	}




	public void collideWith(PlayerShip ent){}

	public void collideWith(EnemyShip ent){}

	public void collideWith(ObstacleBidirectional ent){}

	public void collideWith(ObstacleMonoDirectional ent){}

	public void collideWith(PlayerBullet ent){}

	public void collideWith(EnemyBullet ent){}

	public void collideWith(Reward ent){}

	public abstract void reportCollision(CollisionData data);

	public boolean alive() {
		return getData().getHealth() > 0;
	}
}
