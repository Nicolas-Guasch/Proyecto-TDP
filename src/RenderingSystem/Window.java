package RenderingSystem;

import Engine.Component;
import GameData.GameSettings;
import ParaTestear.Paths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.net.URL;

public class Window extends Component
{

    private static Window instance;
    public static Window GetInstance()
    {
        if(instance==null){
            instance = new Window();
        }
        return instance;
    }
    private JFrame wind;
    private JPanel panel;
    private GameSettings settings = GameSettings.GetInstance();
    private Container container;
    private Window()
    {
       //inicializo ventana
        wind = new JFrame();
        wind.setSize(new Dimension(settings.sizeWindow.width+10,settings.sizeWindow.height+30));
        wind.setVisible(false);
        //wind.setBackground(Color.BLACK);
        wind.setContentPane(new JLabel(new ImageIcon(Paths.Background)));
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setLayout(null);
        container = wind.getContentPane();
        //container.setBackground(settings.background);

        container.setBackground(new Color(0,0,0,0));
        container.setBounds(0,0,0,0);
        container.setSize(settings.sizeWindow);

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
    }
    public void Remove(JComponent component)
    {
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
}
