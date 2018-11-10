package Tools;

import java.util.Map;

public class CompEntry<K extends Comparable<K>,V> implements Comparable<CompEntry<K,V>> , Map.Entry<K,V>
{
    private K key;
    private V value;

    public CompEntry(K k , V v)
    {
        key = k;
        value = v;
    }

    public V value() {
        return value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    public K key() {
        return key;
    }

    @Override
    public int compareTo(CompEntry<K, V> o)
    {
        return key.compareTo(o.key());
    }
}
