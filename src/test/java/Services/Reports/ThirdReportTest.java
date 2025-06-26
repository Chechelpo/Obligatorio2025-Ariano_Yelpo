package Services.Reports;

import Domain.Pelicula;
import Domain.Saga;
import Interfaces.HashCerrado;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;
import Utils.HashTableCerrado.MyHashCerrado;
import Utils.SimpleLinkedList.MyLinkedList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ThirdReportTest {

    @Test
    public void testThirdReportWithMockSagas() {
        // Capturar salida estándar
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Películas
        Pelicula p1 = new Pelicula(new NotNullInteger(1), new NotBlankString("Iron Man"), 100, new NotBlankString("en"), 400); // ingreso: -300
        Pelicula p2 = new Pelicula(new NotNullInteger(2), new NotBlankString("Iron Man 2"), 120, new NotBlankString("en"), 500); // ingreso: -380
        Pelicula p3 = new Pelicula(new NotNullInteger(3), new NotBlankString("Thor"), 90, new NotBlankString("en"), 300); // ingreso: -210
        Pelicula p4 = new Pelicula(new NotNullInteger(4), new NotBlankString("Avengers"), 200, new NotBlankString("en"), 800); // ingreso: -600
        Pelicula p5 = new Pelicula(new NotNullInteger(5), new NotBlankString("Solo"), 80, new NotBlankString("en"), 100); // ingreso: -20

        // Sagas
        Saga marvel = new Saga(new NotNullInteger(101), new NotBlankString("Marvel Saga"));
        marvel.agregarPelicula(p1);
        marvel.agregarPelicula(p2);
        marvel.agregarPelicula(p3);
        marvel.agregarPelicula(p4);

        Saga starwars = new Saga(new NotNullInteger(102), new NotBlankString("Star Wars"));
        starwars.agregarPelicula(p5);

        // Hash de sagas
        HashCerrado<NotNullInteger, Saga> sagas = new MyHashCerrado<>(300);
        sagas.put(marvel.getId(), marvel);
        sagas.put(starwars.getId(), starwars);

        // Ejecutar el reporte
        ThirdReport.thirdReport(sagas);

        // Verificar resultados
        String salida = output.toString();
        assertTrue(salida.contains("101")); // id saga Marvel
        assertTrue(salida.contains("Marvel Saga"));
        assertTrue(salida.contains("4")); // 4 películas
        assertTrue(salida.contains("[1,2,3,4]")); // ids películas

        assertTrue(salida.contains("102")); // id saga Star Wars
        assertTrue(salida.contains("Star Wars"));
        assertTrue(salida.contains("[5]"));
    }

}
