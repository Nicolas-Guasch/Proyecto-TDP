package Engine;


import Components.IPrototype;

abstract class Component <Son extends Component<Son>> implements IComponent
{

    private GameObject _Node_gameObject = null;

    /*
    * these functions are optional to override
    * its why these arent abstracts
    *
    */
    void Update(){}
    void Start() {}
    void FixedUpdate(float deltaTime){}
    void OnEnable(){}
    void OnDisable(){}
    void OnDestroy(){}
    void Awake(){}



    // TODO: mas adelante implementar el tweener

    protected Transform transform()
    {
        return gameObject().transform();
    }

    void setGameObject(GameObject ref){//readOnlySetter

        if(_Node_gameObject == null)
        {
            _Node_gameObject = ref;
        }
        else
        {
            System.out.println("setGameObject is a readonly method, this assign will not be valid");
        }
    }
    public GameObject gameObject()
    {
        return _Node_gameObject; // must be always attatched
    }

    public Component getComponent(Class<Component> ComponentType)
    {
        return gameObject().GetComponent(ComponentType);
    }

    protected static<Son extends Component<Son>> void Destroy(Son c)
    {
        Core.getInstance().waitForFrames(()->
        {
            c.OnDestroy();
            c.gameObject().Remove(c);
        },
        0);
    }



    // -------- activable --------
    private boolean active = true;
    @Override
    public boolean isActive()
    {
        return active;
    }
    public void setActive(boolean active)
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

    public abstract void copy(Son s);
    public abstract Son clone();
}

