package Settings;

import ADTs.Vector2;
import Assets.AssetStore;
import GameData.GameSettings;
import PreLoad.PreloadWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public final class Settings {

	private static Settings instance;
	public static Settings getInstance(){
		instance = (instance==null) ? new Settings():instance;
		return instance;
	}

	private JFrame wind;
	private Container container;
	private JComboBox<String> box;
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
		JButton ok = new JButton("OK");
		ok.addActionListener(this::actionButton);
		Collection<Integer> keys = new HashSet<>();
		keys.add(KeyEvent.VK_ESCAPE);
		keys.add(KeyEvent.VK_ENTER);
		keys.add(KeyEvent.VK_SPACE);
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
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		ok.addActionListener(this::actionButton);


		vsizes = new Vector2[]{
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

		ssizes = new String[vsizes.length];

		for (int i = 0; i < vsizes.length; i++) {
			ssizes[i] = ""+(int)vsizes[i].x()+" x "+(int)vsizes[i].y()+"";
		}



		box = new JComboBox<>(ssizes);


		size.setBounds(120,10,200,50);
		box.setBounds(120,110,100,30);
		ok.setBounds(220,210,80,30);


		container.add(size);
		container.add(ok);
		container.add(box);
		wind.repaint();
		container.repaint();

	}

	private boolean close = false;
	private void actionButton(ActionEvent e){
		if(close)return;
		close = true;
		int i = box.getSelectedIndex();
		Vector2 screenSize = vsizes[i];
		GameSettings.GetInstance().sizeWindow = screenSize.ToDimension();
		wind.setVisible(false);
		wind.dispose();
		new Thread(PreloadWindow::getInstance).start();
	}

}