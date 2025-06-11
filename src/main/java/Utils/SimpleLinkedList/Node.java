package Utils.SimpleLinkedList;

public class Node<K> {
    private K value;
    private Node<K> next;

    public Node(K value) {
        this.value = value;
        this.next = null;
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
}
