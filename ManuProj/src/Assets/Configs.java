package Assets;

import java.awt.*;

public final class Configs {

    private static Configs instance;
    public static Configs getConfigs(){
        if (instance == null){
            instance = new Configs();
        }
        return instance;
    }

    public int canvasWidth = 1200;
    public int canvasHeight = 900;
    public int panelWidth = 300;
    public Dimension windowsSize = new Dimension(canvasWidth+ panelWidth, canvasHeight);
    public Dimension canvasSize = new Dimension(canvasWidth,canvasHeight);

}
