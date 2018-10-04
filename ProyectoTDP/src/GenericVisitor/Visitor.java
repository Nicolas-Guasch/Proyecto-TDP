package GenericVisitor;

import java.util.function.Consumer;

public interface Visitor<TypeVisitable extends Visitable>
{
    void visit(TypeVisitable visitable);
}

