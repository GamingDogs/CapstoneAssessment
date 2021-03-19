import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Rohan More
 * @author Ryan Cosner
 * @project CapstoneAssessment
 */

//References https://www.techiedelight.com/implement-multimap-java/
public class QuoteMultiMap<K, V> {

    //We are using a Treemap because we need case insenestivity for partial and exact matching searches
    private Map<K, Collection<V>> map = new TreeMap<K, Collection<V>>((Comparator<? super K>) String.CASE_INSENSITIVE_ORDER);

    /**
     * Add a value to the multimap with a certain key
     * @param key - Map Key
     * @param value - Map Value
     */
    public void put(K key, V value) {
        map.computeIfAbsent(key, k -> new ArrayList<V>());
        map.get(key).add(value);
    }

    /**
     * Connect the key with a value if the key is not connected to a value
     * @param key - Map Key
     * @param value - Map Value
     */
    public void putIfAbsent(K key, V value) {
        map.computeIfAbsent(key, k -> new ArrayList<>());

        if (!map.get(key).contains(value)) {
            map.get(key).add(value);
        }
    }

    /**
     * Get a collection of values for a certain key
     * @param key - Map Key
     * @return - Return a collection of values of key
     */
    public Collection<V> get(Object key) {
        return map.get(key);
    }

    /**
     * Return a set of all keys in the multimap
     * @return - Return a set of keys
     */
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * Return a set view of mappings contained in this multimap
     * @return - return set as K,V
     */
    public Set<Map.Entry<K, Collection<V>>> entrySet() {
        return map.entrySet();
    }

    /**
     * Get a collection of values in the multimap
     * @return - Return collection of values
     */
    public Collection<Collection<V>> values() {
        return map.values();
    }

    /**
     * Check to see if the map contains a key
     * @param key - Map Key
     * @return - Return true or false
     */
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /**
     * Remove a certain key from the multimap
     * @param key - Map Key
     * @return - Return true or false
     */
    public Collection<V> remove(Object key) {
        return map.remove(key);
    }

    /**
     * Get the size of the multimap
     * Based off values because duplicate keys are combined
     * @return - Return int as size
     */
    public int size() {
        int size = 0;
        for (Collection<V> value : map.values()) {
            size += value.size();
        }
        return size;
    }

    /**
     * Check to see if the multimap is empty
     * @return - Return true or false
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Clear the multimap
     */
    public void clear() {
        map.clear();
    }

    /**
     * Remove a key and value from the multimap
     * @param key - Map Key
     * @param value - Map Value
     * @return - Return true or false
     */
    public boolean remove(K key, V value) {
        if (map.get(key) != null) {
            return map.get(key).remove(value);
        }
        return false;
    }

    /**
     * Replace a keys value with a new value
     * @param key - Map Key
     * @param oldValue - Map Value to be replaced
     * @param newValue - The new Map Value
     * @return - Return true or false
     */
    public boolean replace(K key, V oldValue, V newValue) {
        if (map.get(key) != null) {
            if (map.get(key).remove(oldValue)) {
                return map.get(key).add(newValue);
            }
        }
        return false;
    }
}
