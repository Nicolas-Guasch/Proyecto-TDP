package IAs;

public abstract class AIQueryDecorator implements EntityQuery //AIQuery va a decorar a EntityQuery
{

    protected EntityQuery decorated;

    public AIQueryDecorator(EntityQuery decorated)
    {
        this.decorated = decorated;
    }
}
