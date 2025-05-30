package Utils.LinkedList;


import java.util.Iterator;

public class LinkedList<K extends Comparable<K>> implements Iterable<K> {
    private Node<K> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node<K> getHead() {
        return head;
    }

    public void append(K value){
        Node<K> toAdd = new Node<>(value);
        if(isEmpty()) {
            head = toAdd;
        } else {
            Node<K> temp = head;
            head = toAdd;
            toAdd.setNext(temp);
        }
        size++;
    }

    @Override
    public Iterator<K> iterator() {
        return new LinkedListIterator();
    }
    public class LinkedListIterator implements Iterator<K> {
        private Node<K> current = head;
        public boolean hasNext() {
            return current != null;
        }
        public K next() {
            K value = current.getValue();
            current = current.getNext();
            return value;
        }
    }
}


