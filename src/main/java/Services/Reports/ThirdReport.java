package Services.Reports;

import Domain.Saga;
import Domain.Pelicula;
import Utils.ColeccionConIngresos;
import Interfaces.HashCerrado;
import Interfaces.MyList;
import Semantics.NotNullInteger;
import Utils.QuickSort.QuickSort;
import Utils.SimpleLinkedList.MyLinkedList;

import java.util.ArrayList;

/**
 * Top 5 de las colecciones (sagas) que más ingresos generaron.
 * Si una película no es parte de una saga, se considera una saga unitaria.
 * Datos esperados:
 * ● Id de la colección (o película).
 * ● Título de la colección (o película).
 * ● Cantidad de películas.
 * ● Conjunto de Ids de películas que la componen.
 * ● Ingresos generados.
 */

public class ThirdReport {
    public static void thirdReport(HashCerrado<NotNullInteger,Saga> Saga) {
        ArrayList<ColeccionConIngresos> ranking = new ArrayList<>();

        // Paso 1: armar ranking a partir de cada saga
        for (Saga saga : Saga) {

            //Obtenemos una lista de películas de la saga
            MyLinkedList<Pelicula> peliculas = saga.getMovies();
            int ingresosTotales = 0;
            MyList<Integer> ids = new MyLinkedList<>(); //lista de ids de las peliculas de la saga

            //Vamos sumando los ingresos de cada pelicula de la saga a ingresos totales
            //Y guardamos la id de cada una
            for (Pelicula pelicula : peliculas) {
                ingresosTotales += pelicula.getIncome(); // ingreso por película
                ids.add(pelicula.getId().getValue());     // id de película
            }

            //agregamos la clase ColleccionConIngreso usando los datos calculados anteriormente
            ranking.add(new ColeccionConIngresos(
                    saga.getId().getValue(),
                    saga.getTitle().getValue(),
                    ingresosTotales,
                    ids
            ));
        }

        // Paso 2: ordenar por ingresos
        QuickSort<ColeccionConIngresos> sortQ = new QuickSort<>();
        sortQ.quickSort(ranking);

        // Paso 3: mostrar Top 5
        int i = 0;
        for (ColeccionConIngresos c : ranking) {
            if (i++ == 5){
                break;
            }
            System.out.println(c.getIdColeccion() + ",");
            System.out.println(c.getNombre() + "," +
                    c.getCantidadPeliculas() + "," +
                    formatearIds(c.getIdsPeliculas()) + "," +
                    c.getIngresos());
        }
    }
    private static String formatearIds(MyList<Integer> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        boolean primero = true;
        for (Integer id : lista) {
            if (!primero) sb.append(",");
            sb.append(id);
            primero = false;
        }

        sb.append("]");
        return sb.toString();
    }


}

