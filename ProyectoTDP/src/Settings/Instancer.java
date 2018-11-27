package Settings;

import PreLoad.LoadWindow;

public class Instancer implements Runnable {//fixme uml. No está en uml
    @Override
    public void run() {
        LoadWindow.getInstance();
    }
}
