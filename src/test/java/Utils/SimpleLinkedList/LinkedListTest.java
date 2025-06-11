package Utils.SimpleLinkedList;


import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @org.junit.jupiter.api.Test
    void getSize() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);

        assertEquals(3, list.getSize());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        assertTrue(list.isEmpty());
        list.append(1);

        assertFalse(list.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void append() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.append(1);
        assertEquals(1, list.getHead().getValue());

        list.append(2);
        assertEquals(1, list.getHead().getNext().getValue());
        assertEquals(2, list.getHead().getValue());

    }

    @org.junit.jupiter.api.Test
    void iterator() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
    }
}