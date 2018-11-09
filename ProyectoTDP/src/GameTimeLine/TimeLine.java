package GameTimeLine;

import Engine.Component;
import Engine.EngineGetter;
import Engine.GameObject;
import Entities.Ships.EnemiesBuilders.VaderAMaker;
import Entities.Ships.EnemiesBuilders.VaderBMaker;
import Entities.Ships.EnemiesBuilders.VaderCMaker;
import Entities.Ships.Player.PlayerShip;
import GameData.MatchResult;
import UI.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static RenderingSystem.Backgrounds.bg_sand;
import static RenderingSystem.Backgrounds.bg_space;
import static RenderingSystem.Backgrounds.bg_water;

public final class TimeLine extends Component {

	private static TimeLine instance;

	public static TimeLine getInstance(){
		instance = (instance==null) ? new TimeLine():instance;
		return instance;
	}


	private List<TimePoint> levels;
	private int currentLevel;
	private TimeLine(){
		currentLevel =0;
		levels = new ArrayList<>();
		float backgroundSpeed = 35;
		TimePoint[] _levels = {
				new PlayerAssembler(),
				new PutDeathStar(),
				new TransitionToLevel(bg_space, 1, backgroundSpeed,false),
				new Level(1),


				new TransitionToLevel(bg_water, 2, backgroundSpeed,true),
				new Level(2),
				new Level(2),

				new TransitionToLevel(bg_sand, 3, backgroundSpeed,true),
				new Level(3),
				new Level(3),
				new Level(3),

				new TransitionToLevel(bg_space, 4, backgroundSpeed,false),
				new TransitionToBoss(),
				new SomeBarricades(),
				new RemoveDeathStar(),
				new BossLevel(new VaderAMaker()),
				new BossLevel(new VaderBMaker()),
				new BossLevel(new VaderCMaker()),
				new Cleaner(),
		};
		levels.addAll(Arrays.asList(_levels));
		GameObject.getRoot().addChild().addComponent(this);
	}

	@Override
	public void update() {
		if(!PlayerShip.getInstance().alive()){
			currentLevel().clean();
			MatchResult.getInstance().EmpireWins();
			UI.getInstance().removeUIComponent(PlayerShip.getInstance().getHealthBar());
			setActive(false);
			return;
		}

		if(currentLevel().completed()){
			currentLevel().clean();
			if(hasNextLevel()){
				currentLevel++;
				runTheLevel();
			}
			else{
				MatchResult.getInstance().AllianceWins();
				UI.getInstance().removeUIComponent(PlayerShip.getInstance().getHealthBar());
				setActive(false);
			}
		}
	}

	private boolean hasNextLevel(){
		return currentLevel<levels.size()-1;
	}
	public TimePoint currentLevel() {
		return levels.get(currentLevel);
	}

	private void runTheLevel()
	{

		currentLevel().assembleMoment();
		currentLevel().startMoment();
		setActive(false);
		EngineGetter.Instance().get().waitForFrames(()->{setActive(true);},5);
	}

	public void playLevel() {
		UI.getInstance().startLevel(0);// intro
		EngineGetter.Instance().get().waitForFrames(this::runTheLevel,500);
	}
}