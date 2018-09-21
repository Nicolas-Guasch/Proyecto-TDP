package InputManager;

public class DummyContinueInput extends AbstractContinueInput
{

    private boolean happens = false;



    public DummyContinueInput(boolean initial)
    {
        happens = initial;
    }

    public void setHappens(boolean happens)
    {
        this.happens = happens;
    }

    @Override
    public boolean happens() {
        return happens;
    }

    @Override
    public void Destroy() {
        happens = false;
    }
}
