package Engine;


import java.util.Map;

abstract class Component
{


    void Update(){}
    void Start() {}
    void FixedUpdate(){}
    void OnEnable(){}
    void OnDisable(){}
    void Awake(){}


    private _gameObject ref = null;
    public void setGameObject(GameObject ref){//readOnlySetter
        if(_gameObject == null)
        _gameObject = ref;
        else
            System.out.println("setGameObject is a readonly method, this assign will not be valid");
    }
    public GameObject gameObject()
    {
        return _gameObject;
    } // must be always attatched

    public Component getComponent(Class<Component> ComponentType)
    {
        return gameObject.GetComponent(ComponentType);
    }




}

