package InputSource;

public class InputSourceDirector
{
    private InputSourceBuilder builder;

    public InputSourceDirector(InputSourceBuilder builder)
    {
        this.builder = builder;
    }
    public void setBuilder(InputSourceBuilder builder)
    {
        this.builder = builder;
    }
    public void assemble()
    {
        builder.create();
        builder.createFront();
        builder.createLeft();
        builder.createRight();
        builder.createDown();
        builder.createUp();
        builder.createShoot();
    }
    public InputSource get()
    {
        return builder.get();
    }

}
