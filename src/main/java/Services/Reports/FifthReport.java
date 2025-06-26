package Services.Reports;

import Domain.Actor;
import Utils.ActorStats;
import Domain.Review;
import Interfaces.HashCerrado;
import Interfaces.MyHashTable;
import Interfaces.MyList;
import Semantics.NotNullInteger;
import Utils.HashTableCerrado.MyHashCerrado;
import Utils.QuickSort.QuickSort;
import Utils.SimpleLinkedList.MyLinkedList;

import java.util.ArrayList;

public class FifthReport {

    public static void fifthReport(MyHashTable<NotNullInteger, Actor> actores, MyList<Review> reviews) {
        // Paso 1: construir mapa PeliculaId → lista de actores
        HashCerrado<NotNullInteger, MyLinkedList<Actor>> actoresPorPelicula = new MyHashCerrado<>(50);

        for (Actor actor : actores.values()) {
            for (NotNullInteger idPelicula : actor.getPeliculas().keys()) {
                if (!actoresPorPelicula.containsKey(idPelicula)) {
                    actoresPorPelicula.put(idPelicula, new MyLinkedList<>());
                }
                actoresPorPelicula.get(idPelicula).add(actor);
            }
        }

        // Paso 2: estructura para stats por mes
        HashCerrado<Integer, HashCerrado<NotNullInteger, ActorStats>> statsPorMes = new MyHashCerrado<>(50);

        for (int mes = 1; mes <= 12; mes++) {
            statsPorMes.put(mes, new MyHashCerrado<>(50));
        }

        // Paso 3: recorrer reviews y acumular stats
        for (Review review : reviews) {

            //Obtengo mes de la review
            int mes = review.getFecha().getMonthValue();
            NotNullInteger idPelicula = review.getPelicula().getId();

            // a partir de la película asociada a la review, usando el hash de la parte (1) obtenemos la lista de actores
            MyLinkedList<Actor> actoresEnPelicula = actoresPorPelicula.get(idPelicula);
            if (actoresEnPelicula == null) continue; //salto

            //para cada actor
            for (Actor actor : actoresEnPelicula) {
                //buscamos el bucket asociado al mes de la review en el Hash de statsPorMes (que es otro hash)
                HashCerrado<NotNullInteger, ActorStats> statsMes = statsPorMes.get(mes);
                NotNullInteger actorId = actor.getId();

                ActorStats stats = statsMes.get(actorId);

                //si todavia no se había guardado el actor con las stats, entonces creamos uno:
                if (stats == null) {
                    stats = new ActorStats(actor.getName().getValue());
                    statsMes.put(actorId, stats);
                }

                // Aumenta en 1 la cantidad de películas evaluadas ese mes, solo si es la primera vez que aparece esa película.
                if (!stats.getPeliculasVistas().containsKey(idPelicula)) {
                    stats.getPeliculasVistas().put(idPelicula, true);
                    stats.setCantidadPeliculas(stats.getCantidadPeliculas() + 1);
                }

                //Aumenta en 1 la cantidad total de calificaciones recibidas ese mes.
                stats.setTotalCalificaciones(stats.getTotalCalificaciones() + 1);
            }
        }

        // Paso 4: ordenar y mostrar el mejor actor por mes
        for (int mes = 1; mes <= 12; mes++) {
            ArrayList<ActorStats> lista = new ArrayList<>();
            for (ActorStats stats : statsPorMes.get(mes).values()) { // Obtenemos el Hash con los ActorStats de el mes y lo agregamos al arraylist para ordenarlo
                lista.add(stats);
            }

            QuickSort<ActorStats> sort = new QuickSort<>();
            sort.quickSort(lista);

            if (!lista.isEmpty()) {
                ActorStats mejor = lista.get(0);
                System.out.println(mes + "," +
                        mejor.getNombre() + "," +
                        mejor.getCantidadPeliculas() + "," +
                        mejor.getTotalCalificaciones());
            }
        }
    }
}


