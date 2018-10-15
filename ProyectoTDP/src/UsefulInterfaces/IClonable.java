package UsefulInterfaces;

public interface IClonable<ClonableType extends IClonable<ClonableType>> {
    ClonableType clone();
}
