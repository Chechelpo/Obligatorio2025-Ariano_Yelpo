package Interfaces;

public interface MyHashTable<K,V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    V remove(K key);
    Iterable<V> values();
    Iterable<K> keys();
    int size();
    boolean isEmpty();
}

