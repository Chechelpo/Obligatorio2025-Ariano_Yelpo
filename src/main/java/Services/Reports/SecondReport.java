package Services.Reports;

import Domain.*;
import Semantics.NotNullInteger;
import Utils.QuickSort.QuickSort;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Top 10 de las películas que mejor calificación media tienen por parte de los usuarios.
 * Datos esperados:
 * ● Id de la película.
 * ● Título de la película.
 * ● Calificación media.
 */

public class SecondReport {
    //ESTAS LISTAS DESPUES HAY QUE CAMBIARLAS POR LAS VERDADERAS
    private Hashtable<NotNullInteger, Pelicula> peliculasPorId;
    private LinkedList<Review> reviews;

    //Constructor completo
    public SecondReport(Hashtable<NotNullInteger, Pelicula> peliculasPorId, LinkedList<Review> reviews) {
        this.peliculasPorId = peliculasPorId;
        this.reviews = reviews;
    }

    //Constructor vacío (por las dudas)
    public SecondReport() {
    }

    public void secondReport() {
        // Paso 1: Acumular suma y cantidad de reviews por película
        Hashtable<NotNullInteger, SumaYConteo> mediasPorPelicula = new Hashtable<>();

        // Recorremos todas las reviews y vamos poco a poco rellenando el hash
        // Con sus datos correspondientes (enrealidad es a las clases dentro del hash)
        for (Review review : reviews) {
            NotNullInteger idPelicula = review.getPelicula().getId();
            double valor = review.getEvaluation();

            SumaYConteo datos = mediasPorPelicula.get(idPelicula);
            if (datos == null) {
                datos = new SumaYConteo();
                mediasPorPelicula.put(idPelicula, datos);
            }
            datos.agregar(valor);
        }

        // Paso 2: Crear lista con media ya calculada
        ArrayList<PeliculaConMedia> lista = new ArrayList<>();

        for (Pelicula pelicula : peliculasPorId.values()) {
            NotNullInteger id = pelicula.getId();
            SumaYConteo datos = mediasPorPelicula.get(id);
            if (datos != null && datos.getCantidad() > 0) {
                lista.add(new PeliculaConMedia(pelicula, datos.media()));
            }
        }

        // Paso 3: Ordenar lista y mostrar top 10
        // PeliculaConMedia dentro de si ya tiene implementado el comparable
        // para que lo haga en funcion de la media

        QuickSort<PeliculaConMedia> sortQ = new QuickSort<>();
        sortQ.quickSort(lista);

        // Display: Motramos los 10 primeros de la lista ordenada
        // Los primeros 10 son los que tienen las calificaciones medias mas grandes

        int i = 0;
        for (PeliculaConMedia p : lista) {
            if (i++ == 10) {
                break;
            }
            System.out.println(p.getPelicula().getId() + "," +
                    p.getPelicula().getTitle() + "," +
                    String.format("%.2f", p.getMedia()));
            //esto ultimo hace que sean 2 cifras despues de la coma
        }
    }
}

