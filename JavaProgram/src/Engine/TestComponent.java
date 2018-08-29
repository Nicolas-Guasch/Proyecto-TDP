package Engine;

public class TestComponent extends MonoBehaviour<TestComponent>
{

    //Runs Once at Start of the object
    void Start() {

    }

    //Runs once per frame
    void Update() {

    }

    /**
     * Runs faster than Update
     * @param deltaTime time between last fixedUpdate (0 if it's the first)
     */
    void FixedUpdate(float deltaTime) {
        super.FixedUpdate(deltaTime);
    }

    //Deep Copy (Important to implement)
    @Override
    public void copy(TestComponent s) {

    }

    //Deep Clone
    @Override
    public TestComponent clone() {
        return new TestComponent();
    }


    //Save current internal status
    @Override
    public String save() {
        return null;
    }

    //Load status from data
    @Override
    public void load(String data) {

    }
}
