package RenderingSystem;

import Tools.CompEntry;

import java.util.*;
import java.util.Map.Entry;

public class LayerTable<KeyType extends Comparable<KeyType>,ValueType> implements Iterable<Entry<KeyType,ValueType>>
{

    private SortedMap<KeyType,Layer<KeyType,ValueType>> map;
    private Map<ValueType,Layer<KeyType,ValueType>> reverse;
    private int count;

    public LayerTable()
    {
        map = new TreeMap<>();
        reverse = new HashMap<>();
        count=0;
    }

    public int Count()
    {
        return count;
    }

    public Layer<KeyType,ValueType> get(KeyType key)
    {
        return map.getOrDefault(key, null);
    }

    public ValueType removeOne(KeyType key, ValueType value)
    {
        Layer<KeyType,ValueType> l = map.getOrDefault(key,null);
        if(l!=null)
        {
            l.removeComponent(value);
            count--;
        }
        return value;
    }

    public Layer<KeyType,ValueType> remove(KeyType key)
    {
        Layer<KeyType,ValueType> l = map.getOrDefault(key,null);
        if(l!=null)
        {
            map.remove(key);
        }
        count -= l.Count();
        return l;
    }
    public void removeOne(ValueType value)
    {
        Layer<KeyType,ValueType> l = reverse.getOrDefault(value,null);
        if(l!=null)
        {
            KeyType k = l.getSortFactor();
            l.removeComponent(value);
            if(l.Count()<=0)
            {
                map.remove(k);
            }
            count--;
        }
    }
    public void putOrMove(KeyType key, ValueType value)
    {
        Layer<KeyType,ValueType> layer = reverse.getOrDefault(value,null);

        if(layer!= null) // lo saco de su layer anterior
        {
            layer.removeComponent(value);
            reverse.remove(value);
            if(layer.Count()==0)
            {
                map.remove(layer.getSortFactor());
            }
            count--; //para balancear con el count++;
        }

        layer = map.getOrDefault(key,null);

        if(layer== null)
        {
            layer = new Layer<>(key);
            map.put(key,layer);
        }
        layer.addComponent(value);
        reverse.put(value,layer);
        count++;
    }

    @Override
    public Iterator<Entry<KeyType, ValueType>> iterator()
    {
        Collection<Entry<KeyType,ValueType>> ret = new LinkedList<>();
        for(Layer<KeyType, ValueType> lay : map.values())
        {
            lay.forEach((c)->ret.add(new CompEntry<>(lay.getSortFactor(),c)));
        }
        return ret.iterator();
    }


}
