package RenderingSystem;

import ADTs.Vector2;
import Assets.AssetStore;
import Settings.Settings;
import UI.*;
import Engine.Component;
import GameData.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Map;

public class Window extends Component
{

    private static Window instance;
    private UI ui;
    private Renderizable backgroundRend;


    public static Window GetInstance()
    {
        if(instance==null){
            instance = new Window();
        }
        return instance;
    }

    /**
     *
     */

    private JFrame wind;




    private GameSettings settings = GameSettings.GetInstance();
    private Container container;

    private LayerTable<Float,JComponent> Zfactor;




    private Window()
    {
        initializer();
    }

    private void initializer() {
        // ------- Frame initialize ---------
        wind = new JFrame();

        wind.setSize(new Dimension(settings.sizeWindow.width,settings.sizeWindow.height));
        wind.setVisible(false);
        wind.setResizable(false);
        wind.setBackground(Color.BLACK);
        wind.setContentPane(new JLabel(AssetStore.getIcon("black")));
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //wind.setLayout(null);
        wind.setUndecorated(true);
        if(Settings.fullScreen)
            wind.pack();
        wind.setLocationRelativeTo(null);
        //wind.setVisible(true);
        container = wind.getContentPane();
        container.setBackground(new Color(0,0,0,0));
        container.setBounds(0,0,0,0);
        container.setSize(settings.sizeWindow);

        wind.setIconImage(AssetStore.getImage("main_icon"));

        //------- z Sorting -----------
        Zfactor = new LayerTable<>();

        // ------- Pause stuff ------

        ui = UI.getInstance();
        var panelUI = ui.getUIPanel();
        panelUI.setBounds(0,0,0,0);
        panelUI.setSize(settings.sizeWindow);
        container.add(panelUI);

        SetZ(panelUI, Float.NEGATIVE_INFINITY,false);

        //------------ set cursor image----------


        //TODO: el set relative location: null
        //no se corresponde con esto
        /*
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = AssetStore.getImage("mouseAim");
        Cursor c = toolkit.createCustomCursor(image , new Point(wind.getX(),
                wind.getY()), "img");
        wind.setCursor (c);
        */
    }

    @Override
    public void start() {



    }

    public void update()
    {
        wind.repaint(); //preguntar a biondi cuando hay que hacer el repaint
    }

    public void Show()
    {
        wind.setVisible(true);
    }

    public void AddJComponent(JComponent jcomp)
    {
        container.add(jcomp);
        Zfactor.putOrMove(1f,jcomp);
    }
    public void Remove(JComponent component)
    {
        Zfactor.removeOne(component);
        container.remove(component);

    }
    public void AddInput(MouseListener mouseListener)
    {
        wind.addMouseListener(mouseListener);
    }
    public void AddInput(KeyListener keyListener)
    {
        wind.addKeyListener(keyListener);
    }

    public void RemoveInput(MouseListener mouseListener)
    {
        wind.removeMouseListener(mouseListener);
    }
    public void RemoveInput(KeyListener keyListener)
    {
        wind.removeKeyListener(keyListener);
    }

    public void SetZ(JComponent jcomp, float zcomponent, boolean ReSortNow)
    {
        Zfactor.putOrMove(zcomponent,jcomp);
        if(ReSortNow)
        {
            ReSort();
        }
    }
    public void SetZ(JComponent jcomp, float zcomponent)
    {
        Zfactor.putOrMove(zcomponent,jcomp);
    }

    public void ReSort()
    {
        int i = 0;
        for(Map.Entry<Float, JComponent> entry : Zfactor)
        {
            container.setComponentZOrder(entry.getValue(),i);
            i++;
        }
    }


    public void ShowPause() {
        ui.pause(true);
    }

    public void HidePause() {
        ui.pause(false);
    }

    public void setAsBackground(Renderizable rend) {
        this.backgroundRend = rend;
        //222190
        SetZ(rend.Sprite(),222191+10,true);
    }

    public void setColor(Color color) {
        wind.setBackground(color);
        container.setBackground(color);
        System.out.println("Window::setColor "+color);
        wind.repaint();
    }

    public Vector2 GetPhaseShift() {
        var x = wind.getBounds().x;
        var y = wind.getBounds().y;
        return new Vector2(x,y);
    }

    public boolean InFocus() {
        return wind.hasFocus();
    }
}
