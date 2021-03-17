import java.util.*;

/**
 * @author Rohan More
 * @author Ryan
 * @project Capstone
 */
public class QuoteMultiMap<K, V> {
    private Map<K, Collection<V>> map = new TreeMap<K, Collection<V>>((Comparator<? super K>) String.CASE_INSENSITIVE_ORDER);

    public void put(K key, V value) {
        map.computeIfAbsent(key, k -> new ArrayList<V>());
        map.get(key).add(value);
    }

    public void putIfAbsent(K key, V value) {
        map.computeIfAbsent(key, k -> new ArrayList<>());

        if (!map.get(key).contains(value)) {
            map.get(key).add(value);
        }
    }

    public Collection<V> get(Object key) {
        return map.get(key);
    }

    public Set<K> keySet() {
        return map.keySet();
    }

    public Set<Map.Entry<K, Collection<V>>> entrySet() {
        return map.entrySet();
    }

    public Collection<Collection<V>> values() {
        return map.values();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public Collection<V> remove(Object key) {
        return map.remove(key);
    }

    public int size() {
        int size = 0;
        for (Collection<V> value : map.values()) {
            size += value.size();
        }
        return size;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clear() {
        map.clear();
    }

    public boolean remove(K key, V value) {
        if (map.get(key) != null) {
            return map.get(key).remove(value);
        }
        return false;
    }

    public boolean replace(K key, V oldValue, V newValue) {
        if (map.get(key) != null) {
            if (map.get(key).remove(oldValue)) {
                return map.get(key).add(newValue);
            }
        }
        return false;
    }
}
