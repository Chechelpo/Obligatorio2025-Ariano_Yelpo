package Utils.HashTable;

import Interfaces.MyHashTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
public class HashTable<K, V> implements Iterable<Entry<K, V>>, MyHashTable<K, V> {
    private static final double LOAD_FACTOR = 0.75;

    private LinkedListHash<K, V>[] buckets;
    private int size;
    private int capacity;

    public HashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.buckets = new LinkedListHash[capacity];
        this.size = 0;
    }

    public HashTable() {
        this(32); // Valor por defecto si no se especifica
    }

    private int getIndex(K key) {
        int hash = key.hashCode();
        return Math.abs(hash) % capacity;
    }

    public void put(K key, V value) {
        if ((double) size / capacity > LOAD_FACTOR) {
            rehash();
        }

        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedListHash<>();
        }

        if (!buckets[index].containsKey(key)) {
            size++;
        }

        buckets[index].addOrReplace(key, value);
    }

    public V get(K key) {
        int index = getIndex(key);
        if (buckets[index] == null) return null;
        return buckets[index].get(key);
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        return buckets[index] != null && buckets[index].containsKey(key);
    }

    public V remove(K key) {
        int index = getIndex(key);
        if (buckets[index] == null) return null;
        V removed = buckets[index].remove(key);
        if (removed != null) size--;
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterable<V> values() {
        List<V> lista = new ArrayList<>();
        for (LinkedListHash<K, V> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    lista.add(entry.getValue());
                }
            }
        }
        return lista;
    }
    public Iterable<K> keys() {
        List<K> lista = new ArrayList<>();
        for (LinkedListHash<K, V> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    lista.add(entry.getKey());
                }
            }
        }
        return lista;
    }


    private void rehash() {
        int oldCapacity = capacity;
        capacity *= 2;
        LinkedListHash<K, V>[] oldBuckets = buckets;
        buckets = new LinkedListHash[capacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldBuckets[i] != null) {
                for (Entry<K, V> entry : oldBuckets[i]) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            int currentBucket = 0;
            Iterator<Entry<K, V>> bucketIterator = null;

            private void advance() {
                while ((bucketIterator == null || !bucketIterator.hasNext()) && currentBucket < capacity) {
                    if (buckets[currentBucket] != null) {
                        bucketIterator = buckets[currentBucket].iterator();
                    }
                    currentBucket++;
                }
            }

            @Override
            public boolean hasNext() {
                advance();
                return bucketIterator != null && bucketIterator.hasNext();
            }

            @Override
            public Entry<K, V> next() {
                advance();
                return bucketIterator.next();
            }
        };
    }
}
