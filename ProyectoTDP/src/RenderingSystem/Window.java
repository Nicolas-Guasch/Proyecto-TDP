package RenderingSystem;

import UI.*;
import Engine.Component;
import GameData.GameSettings;
import Stuff.Paths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Map;

public class Window extends Component
{

    private static Window instance;
    private final UI ui;


    public static Window GetInstance()
    {
        if(instance==null){
            instance = new Window();
        }
        return instance;
    }
    private JFrame wind;




    private GameSettings settings = GameSettings.GetInstance();
    private Container container;

    private LayerTable<Float,JComponent> Zfactor;




    private Window()
    {
       // ------- Frame initialize ---------
        wind = new JFrame();

        wind.setSize(new Dimension(settings.sizeWindow.width+600,settings.sizeWindow.height+30));
        wind.setVisible(false);
        wind.setResizable(false);
        wind.setBackground(Color.BLACK);
        wind.setContentPane(new JLabel(new ImageIcon(Paths.Background)));
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setLayout(null);
        wind.setUndecorated(true);
       //wind.pack();
        //wind.setLocationRelativeTo(null);
        //wind.setVisible(true);
        container = wind.getContentPane();
        container.setBackground(new Color(0,0,0,0));
        container.setBounds(0,0,0,0);
        container.setSize(settings.sizeWindow);



        //------- z Sorting -----------
        Zfactor = new LayerTable<>();

        // ------- Pause stuff ------

        ui = UI.getInstance();
        var panelUI = ui.getUIPanel();
        panelUI.setBounds(0,0,0,0);
        panelUI.setSize(settings.sizeWindow);
        container.add(panelUI);

        SetZ(panelUI,222190,false);



    }
    public void Update()
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
}
