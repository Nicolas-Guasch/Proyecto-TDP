package Map;

import javax.swing.*;

import GUI.IUpdatable;
import GUI.Window;
import GameMaster.DoInFrames;
import GameMaster.DoWhen;
import GameObjects.GameObject;

public class GraphicObject implements IUpdatable {
	protected GameObject object;
	protected JLabel model;
	
	public GraphicObject(GameObject o, JLabel l) {
		object = o;
		model = l;
	}
	
	public JLabel getLabel(){
		return model;
	}

	public GameObject getObject(){
		return object;
	}
	


	
	public void update(Map map) {
		map.onUpdate(this);
		object.update(map);

		int x = object.getX();
		int y = object.getY();

		model.setIcon(object.getSprite());
		model.setBounds(x, y, 182, 200);
	}

	@Override
	public void destroyMe(Map map) {
		destroy();
	}



	public void destroy() {

		ImageIcon ic = (ImageIcon) object.getSprite();
		ic.getImage().setAccelerationPriority(0);
		model.setIcon(ic);
		final long t = System.currentTimeMillis() + 4000;


		new DoInFrames(400000000,
		()->
		{
			model.setVisible(false);
			Window.GetWindow().removeComponent(model);
		});



	}
}
