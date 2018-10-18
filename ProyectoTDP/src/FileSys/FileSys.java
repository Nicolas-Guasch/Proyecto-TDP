package FileSys;

public final class FileSys {

    private static FileSys instance;
    public static FileSys getInstance(){
        instance = (instance==null) ? new FileSys():instance;
        return instance;
    }

    private IFileManager currentFileManager;

    private FileSys(){
        currentFileManager = new PlaceHolderFileManager();
    }

    public IFileManager GetFileManager(){
        return currentFileManager;
    }

}