package Components;

public interface IReadOnlyMemento<T extends IReadOnlyMemento<T>>
{
    String save();
    T load(String s);
}
