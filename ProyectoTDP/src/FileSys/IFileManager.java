package FileSys;


public interface IFileManager
{
    /**
     * load text in a existent file, or creates a new file.
     *
     * @Requires : File exists,  extension must contains 4 characters (.meta for example)
     * @param path RelativePathOfTheFile (name without extension if is created, else create as .meta)
     * @param text
     * @param append
     */
    void save(String path, String text, boolean append);
    /**
     * loads the contents of a file
     * @Requires : File exists,  extension must contains 4 characters (.meta for example)
     * @param path RelativePathOfTheFile (name without extension)
     * @return content of the file
     */
    String load(String path);
    /**
     * loads the contents of a file
     * and returns it divided in lines
     * @Requires : File exists,  extension must contains 4 characters (.meta for example)
     * @param path RelativePathOfTheFile (name without extension)
     * @return Iterable of lines
     */
    Iterable<String> loadLines(String path);

    /**
     * loads the contents of a file
     * @Requires : File exists,  extension must contains 4 characters (.meta for example)
     * @param path RelativePathOfTheFile (name without extension)
     * @param regex Regular Expression
     * @return Iterable of parts
     */
    Iterable<String> loadSplitedByRegex(String path, String regex);
}
