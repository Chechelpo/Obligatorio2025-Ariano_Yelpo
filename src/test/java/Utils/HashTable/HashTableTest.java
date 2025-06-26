package Utils.HashTable;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    @Test
    public void testPutAndGet() {
        HashTable<String, Integer> hash = new HashTable<>();
        hash.put("uno", 1);
        hash.put("dos", 2);

        assertEquals(1, hash.get("uno"));
        assertEquals(2, hash.get("dos"));
        assertNull(hash.get("tres"));
    }

    @Test
    public void testReplaceValue() {
        HashTable<String, String> hash = new HashTable<>();
        hash.put("clave", "valor1");
        hash.put("clave", "valor2");

        assertEquals("valor2", hash.get("clave"));
        assertEquals(1, hash.size());
    }

    @Test
    public void testContainsKey() {
        HashTable<String, Boolean> hash = new HashTable<>();
        hash.put("x", true);

        assertTrue(hash.containsKey("x"));
        assertFalse(hash.containsKey("y"));
    }

    @Test
    public void testRemove() {
        HashTable<String, Integer> hash = new HashTable<>();
        hash.put("a", 10);
        hash.put("b", 20);

        assertEquals(10, hash.remove("a"));
        assertNull(hash.remove("z"));
        assertFalse(hash.containsKey("a"));
        assertEquals(1, hash.size());
    }

    @Test
    public void testIsEmptyAndSize() {
        HashTable<Integer, String> hash = new HashTable<>();
        assertTrue(hash.isEmpty());
        hash.put(1, "A");
        hash.put(2, "B");
        assertFalse(hash.isEmpty());
        assertEquals(2, hash.size());
    }

    @Test
    public void testValuesAndKeys() {
        HashTable<String, Integer> hash = new HashTable<>();
        hash.put("A", 10);
        hash.put("B", 20);
        hash.put("C", 30);

        Set<String> keys = new HashSet<>();
        for (String k : hash.keys()) keys.add(k);

        Set<Integer> values = new HashSet<>();
        for (Integer v : hash.values()) values.add(v);

        assertTrue(keys.containsAll(Set.of("A", "B", "C")));
        assertTrue(values.containsAll(Set.of(10, 20, 30)));
    }

    @Test
    public void testIterator() {
        HashTable<String, Integer> hash = new HashTable<>();
        hash.put("X", 100);
        hash.put("Y", 200);

        Set<String> keys = new HashSet<>();
        for (Entry<String, Integer> entry : hash) {
            keys.add(entry.getKey());
        }

        assertTrue(keys.contains("X"));
        assertTrue(keys.contains("Y"));
    }

    @Test
    public void testRehashingIncreasesCapacity() {
        HashTable<Integer, String> hash = new HashTable<>(4);
        for (int i = 0; i < 100; i++) {
            hash.put(i, "valor" + i);
        }

        assertEquals("valor99", hash.get(99));
        assertEquals(100, hash.size());
    }
}
