package Map;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JLabel;

import Controllers.AbstractController;
import Controllers.Controller;
import GUI.IUpdatable;
import GUI.Window;
import GameObjects.*;

public final class Map extends SuperMap{

	private HashMap<GameObject, GraphicObject> gameobject_to_graphicobject;
	Collection<IUpdatable> list;

	Queue<IUpdatable> toDestroy;
	Queue<IUpdatable> toAdd;
	Window wind;




	private static Map instance;

	public static Map newInstance(Window w){
		if (instance == null){
			instance = new Map(w);
		}
		return instance;
	}

	public static Map getInstance() {  //TODO: crear excepciones
		if (instance == null)
			throw new MapException("incicializa el mapa pete");
		return instance;
	}

	private Map(Window w) {
		gameobject_to_graphicobject = new HashMap<>();
		list = new LinkedList<>();

		wind = w;
		toDestroy = new LinkedBlockingQueue<>();
		toAdd = new LinkedBlockingQueue<>();
	}



	  public void run() {
		   wind.Show();
	    }






	public void add(GameObject o)
	{
		JLabel l = wind.add(o.getUbication(), o.getSprite());
		GraphicObject ret =  new GraphicObject(o, l);
		toAdd.add(ret);
		gameobject_to_graphicobject.put(o,ret);
	}

	public void update() {

		while(!toDestroy.isEmpty()){
			list.remove(toDestroy.remove());
		}
		while(!toAdd.isEmpty()){
			list.add(toAdd.remove());
		}

		for (IUpdatable o : list) {
			o.update(this);
		}

	}

	public void destroy(GraphicObject o){
		list.remove(o);
	}


	public void add(IUpdatable upda)
	{
		list.add(upda);
	}
	public void remove(IUpdatable upda)
	{
		toDestroy.add(upda);
	}

	public void onUpdate(Ship ship) {
	}

	public void onUpdate(AbstractController controller) {
	}

	public void onUpdate(GraphicObject graphicObject) {
	}

	public void onUpdate(Bullet o) {
	}

	public void destroy(GameObject gam)
	{
		GraphicObject go = gameobject_to_graphicobject.getOrDefault(gam,null);
		remove(go);
	}

	public void destroy(AbstractController controller) {

	}

	public void addController(AbstractController cont) {
		add(cont);
	}
}


