package Utils.SimpleLinkedList;


import Interfaces.MyList;

import java.util.Iterator;

public class MyLinkedList<K> implements Iterable<K>, MyList<K> {
    private Node<K> head;
    private int size;

    public MyLinkedList() {
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

    public void add(K object){
        Node<K> toAdd = new Node<>(object);
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


