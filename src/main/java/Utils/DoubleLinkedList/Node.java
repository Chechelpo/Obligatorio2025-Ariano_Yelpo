package Utils.DoubleLinkedList;

public class Node<K> {
    private K value;
    private Node<K> next;
    private Node<K> prev;

    public Node(K value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    public Node<K> getNext() {
        return next;
    }

    public void setNext(Node<K> next) {
        this.next = next;
    }

    public Node<K> getPrev() {
        return prev;
    }

    public void setPrev(Node<K> prev) {
        this.prev = prev;
    }
}
