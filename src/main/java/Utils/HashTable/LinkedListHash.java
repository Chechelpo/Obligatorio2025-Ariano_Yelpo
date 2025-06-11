package Utils.HashTable;


import Utils.SimpleLinkedList.Node;

import java.util.Iterator;

public class LinkedListHash<K, V> implements Iterable<Entry<K, V>> {
    private Node<Entry<K, V>> head;
    private int size;

    public void addOrReplace(K key, V value) {
        Node<Entry<K, V>> current = head;
        while (current != null) {
            if (current.getValue().getKey().equals(key)) {
                current.getValue().setValue(value);
                return;
            }
            current = current.getNext();
        }
        Node<Entry<K, V>> newNode = new Node<>(new Entry<>(key, value));
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public V get(K key) {
        Node<Entry<K, V>> current = head;
        while (current != null) {
            if (current.getValue().getKey().equals(key)) {
                return current.getValue().getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean containsKey(K key) {
        Node<Entry<K, V>> current = head;
        while (current != null) {
            if (current.getValue().getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public V remove(K key) {
        Node<Entry<K, V>> current = head;
        Node<Entry<K, V>> previous = null;

        while (current != null) {
            if (current.getValue().getKey().equals(key)) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                size--;
                return current.getValue().getValue();
            }
            previous = current;
            current = current.getNext();
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private Node<Entry<K, V>> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Entry<K, V> next() {
                Entry<K, V> value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }
}
