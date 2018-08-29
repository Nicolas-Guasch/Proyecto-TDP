package Engine;

import Components.IMemento;
import Components.IPrototype;

public abstract class MonoBehaviour<Son extends  MonoBehaviour<Son>> extends Component<Son> implements IMemento
{
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
    public void OnCollisionEnter(CollisionData data){}

}
