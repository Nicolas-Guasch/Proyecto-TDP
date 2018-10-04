package GenericVisitor;

import java.util.function.Consumer;

public interface MonoVisitor<TypeVisitable extends Visitable>
{
    void visit(TypeVisitable visitable);
}

