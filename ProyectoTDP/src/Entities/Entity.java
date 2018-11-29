package Entities;

import Engine.Action;
import Engine.IGameObject;
import Observer.IBroadcaster;
import Collisions.HitBox;
import Engine.Component;
import Collisions.CollisionData;
import EntitiesVisitor.VisitorEntity;
import GameData.GameSettings;
import RenderingSystem.Renderizable;

public abstract class Entity {

	private IGameObject referenced;
	private Action doOnDeath;

	protected EntityData data; // tiene setter y getter, pero la hago protected por comodidad
	protected VisitorEntity visitor;

	protected Entity(IGameObject referenced){
		this.referenced = referenced;
		data = GameSettings.GetInstance().PlaceHolderData();
	}


	public IBroadcaster<Float> observerHealth(){
		return data.getHealthObservable();
	}

	public void setOnDeath(Action doOnDeath){
		this.doOnDeath = doOnDeath;
	}

	public void onDeath(){
		if(doOnDeath !=null)
		{
			doOnDeath.invoke();
		}
	}


	/**
	 * IMPORTANT: you can set the data only once
	 * @param data data to set
	 */
	public void setData(EntityData data){
		if (this.data != null) {
			this.data = data;
		}
	}
	public EntityData data(){
		return data;
	}
	public void setRenderer(Renderizable rend){
		referenced.setRenderer(rend);
	}
	public void setHitBox(HitBox hitBox){
		referenced.addHitBox(hitBox);
	}
	public void addBehaviour(Component comp) {
		referenced.addComponent(comp);
	}
	public IGameObject referenced() {
		return referenced;
	}
	public boolean alive() {
		if(data == null) return false;
		return data().getHealth() > 0;
	}
	public final void reportCollision(CollisionData data){
		data.their().accept(visitor);
	}
	//implement with: visitor.visit(this);
	public abstract void accept(VisitorEntity visitor);

	public void setVisitor(VisitorEntity visitor) {
		this.visitor = visitor;
	}
}
