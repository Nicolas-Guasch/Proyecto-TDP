package FileSys;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class PlaceHolderFileManager implements IFileManager {


    PlaceHolderFileManager(){

    }

    @Override
    public void save(String path, String text, boolean append) {

    }

    @Override
    public String load(String path) {
        return "";
    }

    @Override
    public Iterable<String> loadLines(String path) {
        return new LinkedList<>();

    }

    @Override
    public Iterable<String> loadSplitedByRegex(String path, String regex) {
        return new LinkedList<>();
    }
}
