package Engine;


import Collisions.CollisionData;
import Engine.Components.IActivable;
import Engine.Components.Transform;


/** Esta va en español porque es muy importante qeu quede claro como funciona
 *
 *  la mayoría de los comportamientos de los objetos en el juego seran modelados
 *  por clases derivadas de esta
 *
 *  Se pueden sobreescribir las funciones de start, update, physicsUpdate(delta:float)
 *  onDestroy, onDisable, onEnable, ademas de las de IActivable como lo son
 *  setActive(active:boolean) y isActive:boolean
 *
 *  Ademas tenesmos acceso al transform del GameObject al cual modificamos su comportamiento
 *  es muy importante que cada instancia de estos componentes sea para modelar
 *  a UN solo GameObject, ya que,
 *  GameObject::AddComponent y GameObject::RemoveComponent
 *  tienen la responsabilidad de enlazar los ocmponentes necesarios para funcionar
 *
 *  Tambien tendremos, por cuestiones de comodidad (sacrificando diseño)
 *  acceso al GameObject en cuestión desde estas clases hijas de Component
 *
 *
 *
 *  IMPORTANTE: transform() y gameObject() no deben ser utilizadas en el
 *  constructor ni en ningun método que se ejecuto previo a ser attatcheado
 *  a un gameObject, ya que, esta operacion sera la que provea la informacion
 *  necesaria para poder manipular el Transform y el GameObject
 *
 */
public abstract class Component implements IActivable
{

    private GameObject _Node_gameObject = null;

    /*
    * these functions are optional to override
    * its why these aren't abstracts
    */
    public void start(){}
    public void update(){}

    protected long frameCounter(){
        return EngineGetter.Instance().get().frameCounter();
    }

    /** not used in this project, but the core
     *  is generic or 2D games, and could need this
     *
     * @param deltaTime time between lastCall and these
     */
    void PhysicsUpdate(float deltaTime){}


    public void OnEnable(){}
    public void OnDisable(){}
    public void OnDestroy(){}
    @Deprecated
    public void OnCollisionEnter(CollisionData data){}

    public final void DestroyComponent()
    {
        OnDestroy();
        _Node_gameObject = null;
    }


    public Transform transform()
    {
        if(gameObject()==null)return null;
        return gameObject().transform();
    }

    protected void setGameObject(GameObject ref)
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

