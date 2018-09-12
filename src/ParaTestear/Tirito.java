package ParaTestear;

import Engine.Component;
import Engine.Vector2;

public class Tirito extends Component
{

    public Vector2 Speed;

    public Tirito(Vector2 speed, int oper)
    {
        Speed = speed;
        this.oper = oper;
    }

    int i=2;
    boolean derecha = true;
    boolean rare = true;

    int oper;

    @Override
    public void Update()
    {
        transform().MoveTowards(Speed.sum(Speed.versor().rotateUnary(0.03f).prod(i)));
        if(transform().position().length()>10000){
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
