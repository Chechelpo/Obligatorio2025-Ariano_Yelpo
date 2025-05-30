package Utils.DoubleLinkedList;

import java.util.Iterator;

public class DoubleLinkedList<K> implements Iterable<Node<K>> {
    private Node<K> head;
    private Node<K> tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Node<K> node){
        if(isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    @Override
    public Iterator<Node<K>> iterator() {
        return new DoubleLinkedListIterator();
    }
    public class DoubleLinkedListIterator implements Iterator<Node<K>> {
        private Node<K> current;
        public boolean hasNext() {
            return current != null;
        }
        public Node<K> next() {
            Node<K> temp = current;
            current = current.getNext();
            return temp;
        }
    }
}
