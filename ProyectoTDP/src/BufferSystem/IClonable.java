package BufferSystem;

public interface IClonable<Prototype extends IClonable<Prototype>>
{
    Prototype clone();
}
