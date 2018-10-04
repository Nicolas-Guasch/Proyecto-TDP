package GenericVisitor;

import java.util.function.Consumer;

public interface Visitable<Type extends Visitable<Type>> extends Consumer<Visitor<Type>>
{

}
