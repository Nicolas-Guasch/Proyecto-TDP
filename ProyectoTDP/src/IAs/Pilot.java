package IAs;

import Engine.Component;
import Entities.Ships.Ship;

//Must be attatched to a ship
public class Pilot extends Component
{
    private final Ship ship;
    private EntityQuery handler;
    private float Speed;

    public Pilot(EntityQuery handler, Ship ship, float speed) {
        this.handler = handler;
        this.ship = ship;
        Speed = speed;
    }

    @Override
    public void update()
    {
        var top = handler.whereToSee(ship);
        if(transform()==null)return;
        transform().setTop(top);
        transform().moveTowards(handler.whereToMove(ship).prod(Speed));
    }

    public EntityQuery getHandler() {
        return handler;
    }

    public void setHandler(EntityQuery handler) {
        this.handler = handler;
    }


    public float speed() {
        return Speed;
    }

    public void setSpeed(float v) {
        Speed = v;
    }

    public Pilot clone(){
        return new Pilot(handler,ship,speed());
    }
}
