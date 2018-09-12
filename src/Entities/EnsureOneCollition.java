package Entities;

import Engine.Component;
import Engine.Components.CollisionData;

public class EnsureOneCollition extends Component
{

    @Override
    public void OnCollisionEnter(CollisionData data)
    {
        if(gameObject()!=null && data.Mine().gameObject() == gameObject())
        {
            data.Mine().setActive(false);
            gameObject().getRenderer().Hide();//check null?
        }
    }
}
