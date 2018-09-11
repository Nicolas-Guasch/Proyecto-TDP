package Ships.Bullets;

import BufferSystem.IClonable;
import Engine.Component;

public abstract class BulletBehaviour<SonType extends BulletBehaviour<SonType>> extends Component implements IClonable<SonType>
{


    public abstract SonType clone() ;
}
