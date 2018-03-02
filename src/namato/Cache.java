package namato;

import java.util.HashMap;
import java.util.Map;

public class Cache<K, E> {

    private int capacity;
    private int size;
    private Map<K, E> cache;

    Cache(int capacity) {
        this.capacity = capacity;

        size = 0;
        cache = new HashMap<>();
    }

    public void put(K key, E value) {
        if (size++ + 1 == capacity) {
            size  = 0;
            cache = new HashMap<>();
        }
        cache.put(key, value);
    }

    public E get(K key) { return cache.get(key); }

    public E getOrDefault(K key, E defaultValue) { return cache.getOrDefault(key, defaultValue); }

    public int size() { return size; }
}
