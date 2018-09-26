package GameObjects;

import Map.Map;

import javax.swing.*;

public abstract class Ship extends DestroyableObject {
	protected float speed;
	protected boolean isFiring;
	protected Vector2 dir;
	
	public void update(Map map) {

		updatePosition(map);
	}

	protected abstract void updatePosition(Map m);

	public void destroyMe(Map map) {
		map.destroy(this);
	}

	public void setDirec(Vector2 vect) {
		dir = (vect);

	}

	public void setSprite(Icon s){
		sprite = s;
	}

	public void fire(){
		isFiring = true;

	}

	public void stopFiring(){
		isFiring = false;
	}


	@Override
	protected void destroySelf() {


	}

	public void die(){
		health = 0;
	}
}
