package TesterLevels;

public interface Level
{

    void run(Runnable onWin, Runnable onLoose);
    boolean isRunning();
    void destroy();

}
