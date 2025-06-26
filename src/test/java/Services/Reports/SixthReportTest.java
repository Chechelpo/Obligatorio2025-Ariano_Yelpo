package Services.Reports;

import Domain.Pelicula;
import Domain.Review;
import Domain.Usuario;
import Interfaces.MyList;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.SimpleLinkedList.MyLinkedList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SixthReportTest {

    @Test
    public void testSixthReportTopUserPerGenre() {
        // Capturar salida estándar
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Crear película con géneros
        Pelicula pelicula1 = new Pelicula(new NotNullInteger(1), new NotBlankString("Inception"), 100, new NotBlankString("en"), 200);
        pelicula1.addGenre("Sci-Fi");
        pelicula1.addGenre("Action");

        Pelicula pelicula2 = new Pelicula(new NotNullInteger(2), new NotBlankString("Tenet"), 100, new NotBlankString("en"), 200);
        pelicula2.addGenre("Sci-Fi");

        // Crear usuarios
        Usuario u1 = new Usuario(new NotNullInteger(101));
        Usuario u2 = new Usuario(new NotNullInteger(102));

        // Crear reviews
        MyList<Review> reviews = new MyLinkedList<>();
        // Usuario 1 evalúa 2 películas Sci-Fi + 1 Action
        reviews.add(new Review(4.5, u1, pelicula1, 1700000000));
        reviews.add(new Review(5.0, u1, pelicula2, 1700000100));
        reviews.add(new Review(3.8, u1, pelicula1, 1700000200)); // repetida pero cuenta para Action también

        // Usuario 2 evalúa solo 1 Sci-Fi
        reviews.add(new Review(4.0, u2, pelicula2, 1700000300));

        // Ejecutar el reporte
        SixthReport.sixthReport(reviews);
        // Verificar salida
        String salida = output.toString();
        assertTrue(salida.contains("101,Sci-Fi,3"));   // Neo con 2 Sci-Fi
        assertTrue(salida.contains("101,Action,2"));   // Neo con 1 Action
        assertFalse(salida.contains("102,Action"));    // Trinity no evaluó Action
    }

}
