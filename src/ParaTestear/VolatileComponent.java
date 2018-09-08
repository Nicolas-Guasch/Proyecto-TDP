package ParaTestear;

import Engine.Component;
import Engine.EngineFactory;

public final class VolatileComponent extends Component
{

    private float lifeSeconds;
    public VolatileComponent(float lifeSeconds)
    {
        this.lifeSeconds = lifeSeconds;
    }

    @Override
    public void Start()
    {
        EngineFactory.Instance().get().WaitForSeconds(()->gameObject().Destroy(),lifeSeconds);
    }
}
