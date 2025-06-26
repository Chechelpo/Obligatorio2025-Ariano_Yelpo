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

public class FirstReportTest {

    @Test
    public void testFirstReportWithMockData() {
        // Preparar salida estándar para captura
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Crear películas simuladas
        Pelicula p1 = new Pelicula(new NotNullInteger(1), new NotBlankString("Inception"), 100, new NotBlankString("en"), 200);
        Pelicula p2 = new Pelicula(new NotNullInteger(2), new NotBlankString("Amélie"), 50, new NotBlankString("fr"), 100);
        Pelicula p3 = new Pelicula(new NotNullInteger(3), new NotBlankString("Roma"), 20, new NotBlankString("es"), 70);

        MyHashTable<NotNullInteger, Pelicula> peliculas = new HashTable<>();
        peliculas.put(p1.getId(), p1);
        peliculas.put(p2.getId(), p2);
        peliculas.put(p3.getId(), p3);

        // Crear reviews simuladas
        MyList<Review> reviews = new MyLinkedList<Review>();
        reviews.add(new Review(4.5, null, p1, 1700000000));
        reviews.add(new Review(3.5, null, p1, 1700000001));
        reviews.add(new Review(5.0, null, p2, 1700000002));
        reviews.add(new Review(4.0, null, p3, 1700000003));
        reviews.add(new Review(4.2, null, p3, 1700000004));

        // Ejecutar el reporte
        FirstReport.firstReport(peliculas, reviews);

        // Verificar contenido esperado
        String salida = output.toString();
        assertTrue(salida.contains("1,Inception,2,en"));
        assertTrue(salida.contains("2,Amélie,1,fr"));
        assertTrue(salida.contains("3,Roma,2,es"));
    }

}
