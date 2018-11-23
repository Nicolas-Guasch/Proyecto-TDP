package Settings;

import PreLoad.LoadWindow;

public class Instancer implements Runnable {//fixme uml
    @Override
    public void run() {
        LoadWindow.getInstance();
    }
}
