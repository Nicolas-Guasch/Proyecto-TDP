package FileSys;

class MonoThreadFileManager implements IFileManager {



    @Override
    public synchronized void save(String path, String text, boolean append) {

    }

    @Override
    public synchronized String load(String path) {
        return null;
    }

    @Override
    public synchronized Iterable<String> loadLines(String path) {
        return null;
    }

    @Override
    public synchronized Iterable<String> loadSplitedByRegex(String path, String regex) {
        return null;
    }
}
