package Game.Units.Rewards;
import Game.Units.BasicUnit;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Kinematic.KinematicLine;
import Game.Units.Mods.Life.Life;
import Game.Units.Mods.Life.LifeNoCritic;
import Game.Units.Obstacles.OnCollideObstacleAll;
import Game.Units.OnCollide;

public class FreezeEnemies extends Reward{


    public FreezeEnemies() {
        life=new LifeNoCritic();
        kinematic= new KinematicLine();
        data=new RewardData(10,10);
        onCollide=new OnCollideObstacleAll();
    }

    @Override
    public void accept(OnCollide c) {

    }

    //Runs Once at Start of the object
    void Start()
    {

    }

    //Runs once per frame
    void Update()
    {

    }

    /**
     * Runs faster than Update
     * @param deltaTime time between last fixedUpdate (0 if it's the first)
     */
    void FixedUpdate(float deltaTime)
    {

    }

    //Deep Copy (Important to implement)
    @Override
    public void copy(BasicUnit s) {

    }

    //Deep Clone
    @Override    public FreezeEnemies clone()

    {
        return new FreezeEnemies();
    }


    //Save current internal status
    @Override
    public String save() 
    {
        return "";
    }

    //Load status from data
    @Override
    public void load(String data) 
    {

    }
}
