package DataParsers;

import GameData.ISettingsParser;
import GameData.FileSettingsParser;

public final class ParsersManager {

	private static ParsersManager instance;
	public static ParsersManager getInstance(){
		instance = (instance==null) ? new ParsersManager():instance;
		return instance;
	}

	private ISettingsParser settingsParser;
	private ILevelData levelDataParser;

	private ParsersManager(){
		settingsParser = new FileSettingsParser();
		levelDataParser = new LevelData();
	}

	public ISettingsParser getSettingsParser() {
		return settingsParser;
	}

	public ILevelData getLevelDataParser() {
		return new LevelData();
	}
}