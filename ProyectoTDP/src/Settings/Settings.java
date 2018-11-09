package Settings;

import ADTs.Vector2;
import Assets.AssetStore;
import GameData.GameSettings;
import PreLoad.LoadWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Settings {

    public static boolean fullScreen = false;
    private static Settings instance;
	public static Settings getInstance(){
		instance = (instance==null) ? new Settings():instance;
		return instance;
	}

	private JCheckBox full;
	private JFrame wind;
	private Container container;
	private JComboBox<String> box, difficulty;
	private String[] ssizes;
	private Vector2[] vsizes;


	private Settings(){
		wind = new JFrame("One Rebel Army -> Settings");
		wind.setSize(400,300);
		wind.setLocationRelativeTo(null);
		wind.setIconImage(AssetStore.getImage("main_icon"));
		wind.setVisible(true);
		wind.setResizable(false);
		wind.setBackground(new Color(166, 255, 215));


		container = wind.getContentPane();
		container.setLayout(null);



		JLabel size = new JLabel("Screen Size");
		JLabel dif = new JLabel("Difficulty");
		JButton ok = new JButton("OK");
		ok.addActionListener(this::actionButton);
		Collection<Integer> keys = new HashSet<>();

		keys.add(KeyEvent.VK_ENTER);
		keys.add(KeyEvent.VK_SPACE);
		wind.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
					System.exit(0);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		wind.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(keys.contains(e.getKeyCode())){
					actionButton(null);
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(keys.contains(e.getKeyCode())){
					actionButton(null);
				}
			}public void keyReleased(KeyEvent e) {}
		});
		ok.addActionListener(this::actionButton);



		vsizes = getVsizes();

		ssizes = new String[vsizes.length];

		for (int i = 0; i < vsizes.length; i++) {
			ssizes[i] = ""+(int)vsizes[i].x()+" x "+(int)vsizes[i].y()+"";
		}



		box = new JComboBox<>(ssizes);

		difficulty = new JComboBox<>("Easy,Medium,Hard".split(","));

		full = new JCheckBox("Full Screen");
		full.setSelected(true);


		box.setBounds(120,60,100,30);
		size.setBounds(120,10,200,50);
		difficulty.setBounds(18,60,100,30);
		dif.setBounds(20,10,200,50);

		full.setBounds(120,110,100,30);
		ok.setBounds(120,210,100,30);

		container.add(difficulty);
		container.add(size);
		container.add(dif);
		container.add(ok);
		container.add(full);
		container.add(box);
		wind.repaint();
		container.repaint();
	}

	private Vector2[] getVsizes(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		Vector2 siz = new Vector2(screenSize.width , screenSize.height);
		Vector2[] options = new Vector2[]{
				new Vector2(1280,1024),
				new Vector2(1280,960),
				new Vector2(1280,800),
				new Vector2(1280,768),
				new Vector2(1280,720),
				new Vector2(1280,600),
				new Vector2(1152,864),
				new Vector2(1024,768),
				new Vector2(800,600)
		};

		Vector<Vector2> ve = new Vector<>();

		for (Vector2 opt : options) {
			if(opt.x()<=siz.x() && opt.y() <= siz.y() - 30){
				ve.add(opt);
			}
		}

		Vector2[] ret = new Vector2[ve.size()];
		ve.toArray(ret);
		return ret;
	}


	private final AtomicBoolean close = new AtomicBoolean(false);
	//we are playing with faia

	private synchronized void actionButton(ActionEvent e){
		if(close.get())return;

		close.set(true);
		fullScreen = full.isSelected();
		int i = box.getSelectedIndex();
		Vector2 screenSize = vsizes[i];
		GameSettings.GetInstance().sizeWindow = screenSize.toDimension();
		GameSettings.difficulty = difficulty.getSelectedIndex()+1;
		wind.setVisible(false);
		wind.dispose();
		new Thread(LoadWindow::getInstance).start();
	}

}
