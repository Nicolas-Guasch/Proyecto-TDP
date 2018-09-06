package ParaTestear;

import Engine.Component;
import Engine.Components.Vector2;

public class Tirito extends Component
{

    public Vector2 Speed;

    public Tirito(Vector2 speed, int oper)
    {
        Speed = speed;
        this.oper = oper;
    }

    int i=0;
    boolean derecha = true;
    boolean rare = true;

    int oper;

    @Override
    public void Update()
    {
        transform().MoveTowards(Speed.sum(Vector2.RIGHT().prod(i)));
        if(transform().getPosition().length()>10000){
            Speed = Speed.opposite();
        }
        if(rare){
            if(derecha && i>oper){
                i=oper-1;
                derecha = false;
            }
            if(!derecha && i<-oper)
            {
                i=1-oper;
                derecha=true;
            }
            i = derecha ?i+1 : i-1;
        }

    }
}
