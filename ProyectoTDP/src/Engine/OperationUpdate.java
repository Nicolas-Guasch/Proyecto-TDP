package Engine;

import java.util.function.Consumer;

public class OperationUpdate implements Consumer<Object> {

    private final IUpdatable toUpdate;

    public OperationUpdate(IUpdatable toUpdate){
        this.toUpdate = toUpdate;
    }

    @Override
    public void accept(Object o) {
        toUpdate.update();
    }
}
