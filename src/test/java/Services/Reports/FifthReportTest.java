package Services.Reports;

import Domain.Actor;
import Domain.Pelicula;
import Domain.Review;
import Interfaces.MyList;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;
import Interfaces.MyHashTable;
import Utils.SimpleLinkedList.MyLinkedList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FifthReportTest {

    @Test
    public void testFifthReportByMonth() {
        // Capturar salida estándar
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Crear películas
        Pelicula pelicula1 = new Pelicula(new NotNullInteger(1), new NotBlankString("Matrix"), 100, new NotBlankString("en"), 200);
        Pelicula pelicula2 = new Pelicula(new NotNullInteger(2), new NotBlankString("Matrix Reloaded"), 100, new NotBlankString("en"), 200);

        // Crear actor y asociar películas
        Actor keanu = new Actor(new NotNullInteger(10), new NotBlankString("Keanu Reeves"));
        keanu.agregarPelicula(pelicula1);
        keanu.agregarPelicula(pelicula2);

        MyHashTable<NotNullInteger, Actor> actores = new HashTable<>();
        actores.put(keanu.getId(), keanu);

        // Crear reviews simuladas para mes de enero (timestamp = 1704067200 = 1/1/2024)
        MyList<Review> reviews = new MyLinkedList<>();
        for (int i = 0; i < 3; i++) {
            reviews.add(new Review(4.5, null, pelicula1, 1704067200)); // enero
        }
        for (int i = 0; i < 2; i++) {
            reviews.add(new Review(4.8, null, pelicula2, 1704067200)); // enero
        }

        // Ejecutar reporte
        FifthReport.fifthReport(actores, reviews);

        // Verificar salida
        String salida = output.toString();
        assertTrue(salida.contains("1")); // mes de enero
        assertTrue(salida.contains("Keanu Reeves"));
        assertTrue(salida.contains("2")); // 2 películas
        assertTrue(salida.contains("5")); // 5 calificaciones
    }
}
