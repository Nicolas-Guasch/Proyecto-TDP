package Collisions;

import ADTs.Tuple;
import Engine.Component;
import Engine.EngineGetter;

import java.util.Collection;
import java.util.Vector;

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



    public static final int PLAYER = 0, ENEMIES = 1, PLAYERBULLET = 2,
                    ENEMYBULLET = 3, BARRICADE_A=4, BARRICADE_B=5, REWARDS = 6, CANT = 7;

    private HitBoxesLayer[] layers;

    private Collection<Tuple<Integer,Integer>> conection;

    private String inputData = "0 1,0 3,0 4,0 5,0 6,1 2,3 4,2 4,2 5,3 4";

    private HitBoxesManager()
    {
        layers = new HitBoxesLayer[CANT];
        for (int i = 0; i < CANT; i++)
        {
          layers[i] = new HitBoxesLayer();
        }
        conection = new Vector<>();
        for(String pares: inputData.split(","))
        {
            String[] ar = pares.split(" ");
            int c1 = Integer.parseInt(ar[0]);
            int c2 = Integer.parseInt(ar[1]);
            conection.add(Tuple.get(c1,c2));
        }
    }

    private long i=0;
    @Override
    public void update() {
        if(i<100L)
        {
            i++;return;
        }
        makeACheck();
    }

    public void makeACheck()
    {
        HitBoxesLayer l1,l2;
        for(Tuple<Integer, Integer> c : conection)
        {
            l1 = layers[c.get1()];
            l2 = layers[c.get2()];
            l1.checkLayer(l2);
            l2.checkLayer(l1);
        }
    }

    public void addHitBox(HitBox hitBox, int layer)
    {
        layers[layer].addHitBox(hitBox);
    }

    public void removeHitBox(HitBox hitBox)
    {
        for(var layer : layers)
        {
            if(layer.contains(hitBox))
            {
                layer.removeHitBox(hitBox);
                return;
            }
        }
    }
}
