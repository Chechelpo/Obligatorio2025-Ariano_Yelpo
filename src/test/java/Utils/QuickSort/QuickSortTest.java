package Utils.QuickSort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @Test
    public void testSortIntegersDescending() {
        ArrayList<Integer> lista = new ArrayList<>(List.of(5, 2, 8, 1, 9));
        new QuickSort<Integer>().quickSort(lista);

        assertEquals(List.of(9, 8, 5, 2, 1), lista);
    }

    @Test
    public void testSortStringsDescending() {
        ArrayList<String> lista = new ArrayList<>(List.of("banana", "apple", "cherry", "date"));
        new QuickSort<String>().quickSort(lista);

        assertEquals(List.of("date", "cherry", "banana", "apple"), lista);
    }

    @Test
    public void testSortCustomClassDescending() {
        ArrayList<Alumno> lista = new ArrayList<>();
        lista.add(new Alumno("Ana", 92));
        lista.add(new Alumno("Luis", 85));
        lista.add(new Alumno("Zoe", 99));
        lista.add(new Alumno("Juan", 76));

        new QuickSort<Alumno>().quickSort(lista);

        assertEquals("Zoe", lista.get(0).nombre);
        assertEquals("Juan", lista.get(3).nombre);
    }

    @Test
    public void testEmptyList() {
        ArrayList<Integer> lista = new ArrayList<>();
        new QuickSort<Integer>().quickSort(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    public void testSingleElement() {
        ArrayList<Integer> lista = new ArrayList<>(List.of(42));
        new QuickSort<Integer>().quickSort(lista);
        assertEquals(List.of(42), lista);
    }

    static class Alumno implements Comparable<Alumno> {
        String nombre;
        int nota;

        Alumno(String nombre, int nota) {
            this.nombre = nombre;
            this.nota = nota;
        }

        @Override
        public int compareTo(Alumno otro) {
            return Integer.compare(this.nota, otro.nota);
        }
    }
}
