package Collisions;

import ADTs.Tuple;
import Engine.Component;
import Engine.EngineGetter;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class HitBoxesManager extends Component
{

    private static HitBoxesManager instance;

    public static HitBoxesManager getInstance(){
        if (instance == null) {
            instance = new HitBoxesManager();
            EngineGetter.Instance().get().suscribeToUpdate(instance);
        }
        return instance;
    }


    //LAYERS INDEXES
    public static final int
            PLAYER = 0,
            ENEMIES = 1,
            PLAYERBULLET = 2,
            ENEMYBULLET = 3,
            BARRICADE_BOTH =4,
            BARRICADE_ENEM =5,
            REWARDS = 6,
            SHIELD = 7,
            CANT = 8;

    private HitBoxesLayer[] layers;
    private Map<Integer, Queue<HitBox>> toadd;
    private Map<Integer, Queue<HitBox>> toremove;
    //TODO: hacer un mapeo de int a hitboxlayer
    //con las cosas que hay que agregar, en lugar de hacer un add directo

    private Collection<Tuple<Integer,Integer>> connection;

    private String inputData = "0 1,0 3,0 4,0 5,0 6,1 2," +
                                "2 4,2 5,3 4,1 7,3 7,1 4,1 5";

    private HitBoxesManager()
    {
        layers = new HitBoxesLayer[CANT];
        for (int i = 0; i < CANT; i++)
        {
          layers[i] = new HitBoxesLayer();
        }
        connection = new Vector<>();
        for(String pares: inputData.split(","))
        {
            String[] ar = pares.split(" ");
            int c1 = Integer.parseInt(ar[0]);
            int c2 = Integer.parseInt(ar[1]);
            connection.add(Tuple.get(c1,c2));
        }
        toremove = new TreeMap<>();
        toadd = new TreeMap<>();
        for (int i=0 ; i<CANT ; i++){
            toadd.put(i,new LinkedBlockingQueue<>());
            toremove.put(i,new LinkedBlockingQueue<>());
        }
    }


    @Override
    public void update() {
        if (frameCounter() >= 20) {
            checkQueues();
            makeACheck();
        }
    }

    private void checkQueues() {
        for (int i=0 ; i<CANT ; i++)
        {
            while(!toadd.get(i).isEmpty())
            {
                layers[i].addHitBox(toadd.get(i).remove());
            }
            while(!toremove.get(i).isEmpty())
            {
                layers[i].removeHitBox(toremove.get(i).remove());
            }
        }
    }

    private void makeACheck()
    {
        HitBoxesLayer l1,l2;
        for(Tuple<Integer, Integer> c : connection)
        {
            l1 = layers[c.get1()];
            l2 = layers[c.get2()];
            l1.checkLayer(l2);
            l2.checkLayer(l1);
        }
    }



    public void addHitBox(HitBox hitBox, int layer)
    {
        toadd.get(layer).add(hitBox);
        //layers[layer].addHitBox(hitBox);
    }

    public void removeHitBox(HitBox hitBox)
    {
        for (int i = 0; i < layers.length; i++) {
            HitBoxesLayer layer = layers[i];
            if (layer.contains(hitBox)) {
                //layer.removeHitBox(hitBox);
                toremove.get(i).add(hitBox);
                return; // -------------------<<<< exit point
            }
        }
    }
}
