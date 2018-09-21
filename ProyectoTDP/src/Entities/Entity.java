package Entities;

import Engine.Component;
import Engine.Components.AbstractCollider;
import Engine.Components.CollisionData;
import Engine.GameObject;
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

	//Collider must be attatched to this Entity
	public void setCollider(AbstractCollider collider)
	{
		referenced.addCollider(collider);
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


	//TODO: no funca la sobrecarga, preguntar
	//en la catedra como hacer
	//placeholder dejo todos los metodos
	//public void collideWith(Entity ent){System.out.println("Esto no anda");}


	public abstract void collideWith(PlayerShip ent);

	public abstract void collideWith(EnemyShip ent);

	public abstract void collideWith(ObstacleBidirectional ent);

	public abstract void collideWith(ObstacleMonoDirectional ent);

	public abstract void collideWith(PlayerBullet ent);

	public abstract void collideWith(EnemyBullet ent);

	public abstract void collideWith(Reward ent);

	public abstract void reportCollision(CollisionData data);

}
