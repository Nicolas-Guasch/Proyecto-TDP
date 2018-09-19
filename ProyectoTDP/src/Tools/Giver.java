package Tools;

public interface Giver<Type>
{
    /**
     * Asegura GameObject reference instanciado, con Renderizable oculto
     * @return
     */
    Type get();
}
