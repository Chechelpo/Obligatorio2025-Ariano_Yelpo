package Utils.HashTableCerrado;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashCerradoTest {

    @Test
    public void testPutAndGet() {
        MyHashCerrado<String, Integer> hash = new MyHashCerrado<>(4);
        hash.put("a", 1);
        hash.put("b", 2);
        assertEquals(1, hash.get("a"));
        assertEquals(2, hash.get("b"));
        assertNull(hash.get("z"));
    }

    @Test
    public void testReplaceValue() {
        MyHashCerrado<String, String> hash = new MyHashCerrado<>(4);
        hash.put("clave", "valor1");
        hash.put("clave", "valor2");
        assertEquals("valor2", hash.get("clave"));
        assertEquals(1, hash.size());
    }

    @Test
    public void testContainsKey() {
        MyHashCerrado<Integer, Boolean> hash = new MyHashCerrado<>(4);
        hash.put(5, true);
        assertTrue(hash.containsKey(5));
        assertFalse(hash.containsKey(6));
    }

    @Test
    public void testRemove() {
        MyHashCerrado<String, String> hash = new MyHashCerrado<>(4);
        hash.put("key", "val");
        assertTrue(hash.containsKey("key"));
        hash.remove("key");
        assertFalse(hash.containsKey("key"));
        assertNull(hash.get("key"));
        assertEquals(0, hash.size());
    }

    @Test
    public void testRehash() {
        MyHashCerrado<Integer, String> hash = new MyHashCerrado<>(2);
        for (int i = 0; i < 10; i++) {
            hash.put(i, "v" + i);
        }
        assertEquals(10, hash.size());
        assertEquals("v9", hash.get(9));
    }

    @Test
    public void testKeysAndValues() {
        MyHashCerrado<String, Integer> hash = new MyHashCerrado<>(4);
        hash.put("uno", 1);
        hash.put("dos", 2);
        hash.put("tres", 3);

        Set<String> keys = new HashSet<>();
        for (String k : hash.keys()) keys.add(k);

        Set<Integer> values = new HashSet<>();
        for (Integer v : hash.values()) values.add(v);

        assertTrue(keys.containsAll(Set.of("uno", "dos", "tres")));
        assertTrue(values.containsAll(Set.of(1, 2, 3)));
    }

    @Test
    public void testIterator() {
        MyHashCerrado<String, Integer> hash = new MyHashCerrado<>(4);
        hash.put("a", 1);
        hash.put("b", 2);
        hash.put("c", 3);

        int total = 0;
        for (Integer v : hash) {
            total += v;
        }

        assertEquals(6, total);
    }
}
