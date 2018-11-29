package Settings;

import PreLoad.LoadWindow;

public class Instancer implements Runnable {
    @Override
    public void run() {
        LoadWindow.getInstance();
    }
}
