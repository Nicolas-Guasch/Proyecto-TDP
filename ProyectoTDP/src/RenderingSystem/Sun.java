package RenderingSystem;

import ADTs.IVector2;
import ADTs.Vector2;
import Engine.Component;
import Engine.GameObject;

public final class Sun extends Component {

	private static Sun instance;
	private boolean daytime;

	public void setDaytime(boolean daytime) {
		this.daytime = daytime;
	}

	public static Sun getInstance(){
		if (instance == null) {
			instance = new Sun();
			GameObject.getRoot().addChild().addComponent(instance);
		}
		return instance;
	}

	private IVector2 light = Vector2.get(-1,-1).withLength(111);

	public IVector2 getLight() {
		return light;
	}

	@Override
	public void update() {
		light = light.rotateUnary(0.0004f);
	}

	private Sun(){
		daytime = false;
	}

	public boolean daytime() {
		return daytime;
	}
}