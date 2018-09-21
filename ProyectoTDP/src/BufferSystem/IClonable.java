package BufferSystem;


/**
 * models prototype
 * @param <Prototype>
 */
public interface IClonable<Prototype extends IClonable<Prototype>>
{
    Prototype clone();
}
