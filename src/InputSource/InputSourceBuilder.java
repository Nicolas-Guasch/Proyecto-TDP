package InputSource;

public abstract class InputSourceBuilder
{
    protected InputSource inpSource;

    public void create(){
        inpSource = new InputSource();
    }

    public InputSource get(){
        return inpSource;
    }

    public abstract void createLeft();
    public abstract void createRight();
    public abstract void createUp();
    public abstract void createDown();
    public abstract void createFront();
    public abstract void createShoot();

}



