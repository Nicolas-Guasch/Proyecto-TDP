package Ships.Controls;

import Ships.Bullets.SoloBullet;
import Ships.Entities.SoloShip;
import Ships.Puppets.SoloShipPuppet;

public class SoloShipController extends AbstractShipController<SoloShip,SoloBullet, SoloShipPuppet>
{

    public SoloShipController(SoloShipPuppet puppet, SoloBullet prototype) {
        super(puppet, prototype);
    }
}
