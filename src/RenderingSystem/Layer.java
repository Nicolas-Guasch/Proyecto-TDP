package RenderingSystem;

import java.util.*;


public final class Layer<SortFactor extends Comparable<SortFactor>,ComponentType> implements Iterable<ComponentType> , Comparable<Layer<SortFactor,ComponentType>> {
    private SortFactor SortFactor;
    private Collection<ComponentType> components;

    public Layer(SortFactor sortFactor) {
        components = new HashSet<>();//quiza lista
        SortFactor = sortFactor;
    }

    public void addComponent(ComponentType comp) {
        components.add(comp);
    }

    public void removeComponent(ComponentType comp)
    {
        components.remove(comp);
    }

    public boolean containsComponent(ComponentType comp)
    {
        return components.contains(comp);
    }

    public SortFactor getSortFactor() {
        return SortFactor;
    }

    public Iterator<ComponentType> iterator() {
        return components.iterator();
    }

    public int compareTo(Layer<SortFactor, ComponentType> other) {
       return SortFactor.compareTo(other.getSortFactor());
    }

    public int Count() {
        return components.size();
    }
}
