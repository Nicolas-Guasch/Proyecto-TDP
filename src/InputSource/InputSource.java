package InputSource;

import InputManager.*;

public class InputSource
{
	private AbstractContinueInput left;
	private AbstractContinueInput right;
	private AbstractContinueInput up;
	private AbstractContinueInput down;
	private AbstractDiscreteInput shoot;
	private AbstractDirectionalInput front;

	public void Destroy()
	{
		left.Destroy();
		right.Destroy();
		up.Destroy();
		shoot.Destroy();
		down.Destroy();
		front.Destroy();
	}

	public AbstractDirectionalInput getFront() {
		return front;
	}

	public void setFront(AbstractDirectionalInput front) {
		this.front = front;
	}

	public AbstractDiscreteInput getShoot() {
		return shoot;
	}

	public void setShoot(AbstractDiscreteInput shoot) {
		this.shoot = shoot;
	}

	public AbstractContinueInput getDown() {
		return down;
	}

	public void setDown(AbstractContinueInput down) {
		this.down = down;
	}

	public AbstractContinueInput getUp() {
		return up;
	}

	public void setUp(AbstractContinueInput up) {
		this.up = up;
	}

	public AbstractContinueInput getRight() {
		return right;
	}

	public void setRight(AbstractContinueInput right) {
		this.right = right;
	}

	public AbstractContinueInput getLeft() {
		return left;
	}

	public void setLeft(AbstractContinueInput left) {
		this.left = left;
	}
}
