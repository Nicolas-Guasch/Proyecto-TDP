package RenderingSystem;

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

	private Vector2 light = new Vector2(-1,-1).withLength(111);

	public Vector2 getLight() {
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