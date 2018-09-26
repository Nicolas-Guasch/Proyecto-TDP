package GUI;

import java.awt.*;
import java.awt.event.KeyListener;

import javax.swing.*;

import Assets.Configs;
import Assets.Paths;
import Controllers.Controller;
import GameObjects.Vector2;
import GameObjects.Vector2Int;
import Map.Map;

public class Window {

    private static Window instance;

    private JFrame frame;
    private JPanel panel;
    private Container gameContainer;
    private Container UIcontainer;
    //private Collection<JLabel> objetos;

    


    
    public static Window GetWindow()  {
        if(instance ==null){
           instance = new Window();
        }
        return instance;
    }
    private Window() {
		frame = new JFrame();
		//frame.setContentPane(new JLabel(new ImageIcon(Paths.BACKGROUND)));
		Container c = frame.getContentPane();
		frame.setLayout(null);
		frame.setBounds(0,0, Configs.getConfigs().canvasWidth,Configs.getConfigs().canvasHeight);
		c.setLayout(null);
		//frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);

		Icon ic = new ImageIcon(Paths.BACKGROUND);
		gameContainer = new JLabel(ic);
		UIcontainer = new JLabel("fondoxD");
		UIcontainer.setLayout(new FlowLayout());

		gameContainer.setBounds(Configs.getConfigs().panelWidth,0,Configs.getConfigs().canvasWidth,Configs.getConfigs().canvasHeight);
		UIcontainer.setBounds(0,0,Configs.getConfigs().panelWidth,Configs.getConfigs().canvasHeight);

		frame.setVisible(true);
		gameContainer.setLayout(null);
		//UIcontainer.setLayout(null);

		c.add(gameContainer);
		c.add(UIcontainer);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getRootPane().setSize(Configs.getConfigs().windowsSize);


		frame.setSize(Configs.getConfigs().windowsSize);
		frame.setResizable(false);

		UIcontainer.add(ScoreManager.getInstance().getScore().getJLabel());           //VER BIEN ESTO
		UIcontainer.add(ScoreManager.getInstance().getVida().getJLabel());

    }
    public void update(){
       frame.repaint();
    }

    public void Show() {
        frame.setVisible(true);
    }
    
    public  JLabel add(float x, float y, Icon s) {
		JLabel object = new JLabel(s);
		gameContainer.add(object);
		object.setBounds((int) x, (int)y, 182, 200);
		return object;
    }

    public  JLabel add(Vector2 ubication, Icon s) {
	    	Vector2Int	v = Vector2Int.Implicit(ubication);
		JLabel object = new JLabel(s);
		gameContainer.add(object);
		object.setBounds(v.getX(),v.getY(), s.getIconWidth(),s.getIconHeight());
		return object;
    }
    



    public void addListener(KeyListener k) { frame.addKeyListener(k); }


	public void removeComponent(JComponent model)
	{
		frame.remove(model);
	}
}