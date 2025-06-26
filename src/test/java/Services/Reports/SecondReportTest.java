package Services.Reports;

import Domain.Pelicula;
import Domain.Review;
import Interfaces.MyHashTable;
import Interfaces.MyList;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;
import Utils.SimpleLinkedList.MyLinkedList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SecondReportTest {

    @Test
    public void testSecondReportWithMockData() {
        // Redirigir salida estándar para capturar resultados
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Crear películas simuladas
        Pelicula p1 = new Pelicula(new NotNullInteger(1), new NotBlankString("Interstellar"), 50, new NotBlankString("en"), 300);
        Pelicula p2 = new Pelicula(new NotNullInteger(2), new NotBlankString("Parasite"), 30, new NotBlankString("ko"), 100);
        Pelicula p3 = new Pelicula(new NotNullInteger(3), new NotBlankString("La Vita è Bella"), 40, new NotBlankString("it"), 120);

        MyHashTable<NotNullInteger, Pelicula> peliculas = new HashTable<>();
        peliculas.put(p1.getId(), p1);
        peliculas.put(p2.getId(), p2);
        peliculas.put(p3.getId(), p3);

        // Crear reviews simuladas
        MyList<Review> reviews = new MyLinkedList<>();
        reviews.add(new Review(5.0, null, p1, 1700000000));
        reviews.add(new Review(4.5, null, p1, 1700000001));
        reviews.add(new Review(4.8, null, p2, 1700000002));
        reviews.add(new Review(4.9, null, p3, 1700000003));
        reviews.add(new Review(4.7, null, p3, 1700000004));

        // Ejecutar reporte
        SecondReport.secondReport(peliculas, reviews);

        // Verificación básica de contenido
        String salida = output.toString();
        assertTrue(salida.contains("1,Interstellar"));
        assertTrue(salida.contains("2,Parasite"));
        assertTrue(salida.contains("3,La Vita è Bella"));
        assertTrue(salida.contains("4.75") || salida.contains("4.80") || salida.contains("4.85")); // Medias aproximadas
    }
}
