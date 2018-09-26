package GameObjects;

import Assets.Configs;
import Assets.Paths;
import Map.*;

import javax.swing.*;

public class Player extends  Shooter{
	protected final float playerSpeed = 8.6f;
	protected int damage;
	protected int attackSpeed;
	protected boolean loaded;
	protected long time;
	protected int gunPosition;

	// --------- alternar balas -----
	protected float gunPhaseShift;
	//-------------------------


	protected static Vector2 initialPosition = new Vector2(218,680);
	protected int playerDamage = 20;
	protected int playerAttackSpeed = 300;

	private static Player instance = null;

	public static Player getInstance() {
		if (instance == null)
			instance = new Player();
		return instance;
	}

	private Player() {
		health = 200;
		speed = playerSpeed;
		time=0;
		ubication = initialPosition;
		dir = Vector2.ORIGIN();
		damage = playerDamage;
		sprite = new ImageIcon(Paths.NAVE);
		attackSpeed = playerAttackSpeed;
		loaded = true;
		isFiring = false;
		gunPosition = -7;
		gunPhaseShift = 40;
	}

	//probablemente vaya mas arriba en jerarquia


	
	public void update(Map map) {

		checkFire(map);
		updatePosition(map);

		super.update(map);


	}

	private void checkFire(Map map) {

		if (time < System.currentTimeMillis())
			loaded = true;
		if (loaded && isFiring){
			loaded = false;
			time = System.currentTimeMillis() + attackSpeed;


			Vector2 ubBullet = getUbication().sum(Vector2.RIGHT(gunPosition+gunPhaseShift));
			Bullet b = new PlayerBullet(damage,ubBullet);
			map.add(b);
			gunPhaseShift *= -1;

		}

	}

	@Override
	protected void updatePosition(Map map) {
		map.onUpdate(this);
		float x = ubication.getX();
		float y = ubication.getY();


		x += dir.getX() * speed;
		if(x < -12) //treshold del sprite, adecuar al sprite final /TODO: Magic numbersssssssss
			x = -12;
		if (x > 1000)
			x = 1000;

		y += dir.getY() * speed;
		if(y < 0) //treshold del sprite, adecuar al sprite final
			y = 0;
		if (y > Configs.getConfigs().canvasHeight - 220)
			y = Configs.getConfigs().canvasHeight - 220;

		ubication = new Vector2(x,y);
	}
}
	
	
	
	
	
	


