package Engine;

public class DummyCondition implements Condition {
    private boolean value;

    public DummyCondition(boolean initial){
        this.value = initial;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean ask() {
        return value;
    }
}
