package GameObjects;

public abstract class DestroyableObject extends GameObject{
	protected int health;
	
	public int getHealth() {
		return health;
	}


	public boolean isAlive() {
		return health > 0;
	}

	protected abstract void destroySelf();
	
}
