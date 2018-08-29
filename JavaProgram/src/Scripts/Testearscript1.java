package Scripts;
import Engine.MonoBehaviour;

public class Testearscript1 extends MonoBehaviour<Testearscript1>
{

    //Runs Once at Start of the object
    void Start() 
    {

    }

    //Runs once per frame
    void Update() 
    {

    }

    /**
     * Runs faster than Update
     * @param deltaTime time between last fixedUpdate (0 if it's the first)
     */
    void FixedUpdate(float deltaTime) 
    {
        
    }

    //Deep Copy (Important to implement)
    @Override    public void copy(Testearscript1 s) 

    {

    }

    //Deep Clone
    @Override    public Testearscript1 clone() 

    {
        return new Testearscript1();
    }


    //Save current internal status
    @Override
    public String save() 
    {
        return "";
    }

    //Load status from data
    @Override
    public void load(String data) 
    {

    }
}
