package Engine;


import Engine.Components.CollisionData;
import Engine.Components.IActivable;
import Engine.Components.Transform;

public abstract class Component implements IActivable
{

    private GameObject _Node_gameObject = null;

    /*
    * these functions are optional to override
    * its why these aren't abstracts
    */
    public void Update(){}

    /**
     *
     * @param deltaTime time between lastCall and these
     */
    public void PhysicsUpdate(float deltaTime){}
    public void OnEnable(){}
    public void OnDisable(){}
    public void OnDestroy(){}
    public void OnCollisionEnter(CollisionData data){}



    // TODO: mas adelante implementar el tweener

    public Transform transform()
    {
        return gameObject().getTransform();
    }

    public void setGameObject(GameObject ref)
    {//readOnlySetter

        if(_Node_gameObject == null)
        {
            _Node_gameObject = ref;
        }
    }
    public GameObject gameObject()
    {
        return _Node_gameObject; // must be always attatched
    }






    // -------- activable --------
    private boolean active = true;
    @Override
    public final boolean isActive()
    {
        return active;
    }
    public final void setActive(boolean active)
    {
        if(this.active && !active)
        {
            OnDisable();
        }
        if(!this.active && active)
        {
            OnEnable();
        }
        this.active = active;
    }

}

