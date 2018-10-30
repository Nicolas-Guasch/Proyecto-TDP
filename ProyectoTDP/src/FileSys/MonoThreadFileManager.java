package FileSys;

import java.io.File;
import java.util.*;

class MonoThreadFileManager implements IFileManager {

    // name -> content
    private Map<String,String> lightweightLoads = new TreeMap<>();

    public static String[] commonExtensions = {".txt",".meta",".settings",".json"};


    MonoThreadFileManager(){

    }




    private static String[] usualDirs = {
                "Assets\\Animators\\",
                "Assets\\Bullets\\",
                "Assets\\Ships\\",
                "Assets\\Stuff\\",
                "Assets\\UI\\",
                "Assets\\",
                };
    private Collection<String> currentsWaysToFindAFile(){

    }


    @Override
    public synchronized String load(String path) {
        if(lightweightLoads.containsKey(path.toLowerCase())){
            return lightweightLoads.get(path.toLowerCase());
        }
        StringBuffer current = new StringBuffer();



    }

    @Override
    public synchronized Iterable<String> loadLines(String path) {
        return null;
    }

    @Override
    public synchronized Iterable<String> loadSplitedByRegex(String path, String regex) {
        return null;
    }


    @Override
    public synchronized void save(String path, String text, boolean append) {

    }
}
