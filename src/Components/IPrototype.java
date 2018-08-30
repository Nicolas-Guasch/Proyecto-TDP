package Components;

public interface IPrototype<C extends IPrototype<C>>
{
    C clone();
}

