package FileSys;

class MonoThreadFileManager implements IFileManager {


    @Override
    public void save(String path, String text, boolean append) {

    }

    @Override
    public String load(String path) {
        return null;
    }

    @Override
    public Iterable<String> loadLines(String path) {
        return null;
    }

    @Override
    public Iterable<String> loadSplitedByRegex(String path, String regex) {
        return null;
    }
}
