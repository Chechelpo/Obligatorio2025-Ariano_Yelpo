package Utils.SimpleLinkedList;


import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void getSize() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.getSize());
    }

    @Test
    void isEmpty() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        assertTrue(list.isEmpty());
        list.add(1);

        assertFalse(list.isEmpty());
    }

    @Test
    void append() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        assertEquals(1, list.getHead().getValue());

        list.add(2);
        assertEquals(1, list.getHead().getNext().getValue());
        assertEquals(2, list.getHead().getValue());

    }

    @Test
    void iterator() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
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