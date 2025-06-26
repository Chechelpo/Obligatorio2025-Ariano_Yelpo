package Services.Reports;

import Domain.Director;
import Domain.Pelicula;
import Domain.Review;
import Interfaces.HashCerrado;
import Interfaces.MyList;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;
import Utils.HashTableCerrado.MyHashCerrado;
import Utils.SimpleLinkedList.MyLinkedList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FourthReportTest {

    @Test
    public void testFourthReportWithMockData() {
        // Capturar salida
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Crear películas
        Pelicula p1 = new Pelicula(new NotNullInteger(1), new NotBlankString("Film A"), 100, new NotBlankString("en"), 200);
        Pelicula p2 = new Pelicula(new NotNullInteger(2), new NotBlankString("Film B"), 100, new NotBlankString("en"), 200);

        // Crear director
        Director d1 = new Director(new NotNullInteger(10), new NotBlankString("Spielbergo"));
        d1.agregarPelicula(p1);
        d1.agregarPelicula(p2);

        // Crear hash de directores
        HashCerrado<NotNullInteger, Director> directores = new MyHashCerrado<>(500);
        directores.put(d1.getId(), d1);

        // Crear +100 reviews con medias distintas por película
        ArrayList<Review> reviews = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            reviews.add(new Review(4.5, null, p1, 1700000000 + i));
        }
        for (int i = 0; i < 60; i++) {
            reviews.add(new Review(3.5, null, p2, 1700000060 + i));
        }

        // Lista custom compatible
        MyList<Review> listaReviews = new MyLinkedList<>();
        for (Review r : reviews) listaReviews.add(r);

        // Ejecutar reporte
        FourthReport.fourthReport(directores, listaReviews);

        // Verificar salida
        String salida = output.toString();
        assertTrue(salida.contains("Spielbergo,2")); // 2 películas
        assertTrue(salida.contains("4.0")); // mediana entre 60 x 4.5 y 60 x 3.5
    }

}
