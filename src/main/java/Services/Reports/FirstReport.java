package Services.Reports;

import Domain.Pelicula;
import Utils.PeliculaConConteo;
import Domain.Review;
import Interfaces.MyHashTable;
import Interfaces.MyList;
import Semantics.NotNullInteger;
import Utils.QuickSort.QuickSort;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Tomando en cuenta las películas y las evaluaciones que los usuarios realizaron
 * sobre las mismas, indicar el Top 5 de las películas en idioma original Inglés, Francés,
 * Italiano, Español y Portugues con más evaluaciones por parte de los usuarios.
 * Datos esperados del resultado:
 * ● Id de la película.
 * ● Título de la película.
 * ● Total de evaluaciones.
 * ● Idioma Original
 * **/

public class FirstReport {
    public static void firstReport(MyHashTable<NotNullInteger, Pelicula> peliculasPorId, MyList<Review> reviews) {

        // PASO 1: Crear HASH que tenga como clave id_Película y que guarde la cantidad de reviews que tenga
        Hashtable<NotNullInteger, Integer> conteoPorPelicula = new Hashtable<>();

        // Recorremos la lista de reviews y cada vez que veamos una película repetida
        // Aumentamos el contador de películas en el HASH donde esta la id de la pelicula en cuestión
        for (Review review : reviews) {
            NotNullInteger idPelicula_review = review.getPelicula().getId();
            Integer nroReviews = conteoPorPelicula.get(idPelicula_review);

            // Si el bucket está vacío, entonces lo inicializamos en 0
            if (nroReviews == null) {
                nroReviews = 0;
            }
            conteoPorPelicula.put(idPelicula_review, nroReviews + 1);
        }

        // Paso 2: crear 5 listas para los 5 idiomas requeridos
        ArrayList<PeliculaConConteo> listaEn = new ArrayList<>();
        ArrayList<PeliculaConConteo> listaFr = new ArrayList<>();
        ArrayList<PeliculaConConteo> listaIt = new ArrayList<>();
        ArrayList<PeliculaConConteo> listaEs = new ArrayList<>();
        ArrayList<PeliculaConConteo> listaPt = new ArrayList<>();

        // Agregamos a cada lista las películas con el idioma correspondiente
        for (Pelicula pelicula : peliculasPorId.values()) {
            String idioma = pelicula.getOriginalLanguage();
            Integer conteo = conteoPorPelicula.get(pelicula.getId());

            if (conteo != null && conteo > 0) {
                switch (idioma) {
                    case "en":

                        listaEn.add(new PeliculaConConteo(pelicula, conteo));
                        break;
                    case "fr":
                        listaFr.add(new PeliculaConConteo(pelicula, conteo));
                        break;
                    case "it":
                        listaIt.add(new PeliculaConConteo(pelicula, conteo));
                        break;
                    case "es":
                        listaEs.add(new PeliculaConConteo(pelicula, conteo));
                        break;
                    case "pt":
                        listaPt.add(new PeliculaConConteo(pelicula, conteo));
                        break;
                    default:
                        // Los otros idiomas no importan
                        break;
                }
            }
        }

        // Paso 3: ordenar cada lista y mostrar el Top 5
        mostrarTop5PorIdioma("en", listaEn);
        mostrarTop5PorIdioma("fr", listaFr);
        mostrarTop5PorIdioma("it", listaIt);
        mostrarTop5PorIdioma("es", listaEs);
        mostrarTop5PorIdioma("pt", listaPt);
    }

    // Método auxiliar para ordenar e imprimir el Top 5 de un idioma
    private static void mostrarTop5PorIdioma(String idioma, ArrayList<PeliculaConConteo> lista) {
        ordenarPorConteoDescendente(lista);

        // Imprimir Top 5
        int i = 0;
        for (PeliculaConConteo p : lista) {
            if (i++ == 5) break;
            System.out.println(p.getPelicula().getId() + "," +
                    p.getPelicula().getTitle() + "," +
                    p.getConteo() + "," +
                    p.getPelicula().getOriginalLanguage());
        }
    }

    // Algoritmo de ordenamiento (CAMBIAR CUALQUIER COSA)
    private static void ordenarPorConteoDescendente(ArrayList<PeliculaConConteo> lista) {
        QuickSort<PeliculaConConteo> sortQ = new QuickSort<>();
        sortQ.quickSort(lista);
    }
}
