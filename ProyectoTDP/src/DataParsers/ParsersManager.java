package DataParsers;

public final class ParsersManager {

	private static ParsersManager instance;
	public static ParsersManager getInstance(){
		instance = (instance==null) ? new ParsersManager():instance;
		return instance;
	}

	private ILevelsData levelDataParser;

	private ParsersManager(){
		levelDataParser = new LevelsData();
	}


	public ILevelsData getLevelDataParser() {
		return new LevelsData();
	}
}