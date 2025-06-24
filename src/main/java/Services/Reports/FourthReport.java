package Services.Reports;

import Domain.Director;
import Domain.ResultadoDirector;
import Interfaces.HashCerrado;
import Semantics.NotNullInteger;
import Domain.Review;
import Interfaces.MyList;
import Utils.QuickSort.QuickSort;

import java.util.ArrayList;
import java.util.Collections;

public class FourthReport {

    public static void fourthReport(HashCerrado<NotNullInteger, Director> directores, MyList<Review> reviews) {

        ArrayList<ResultadoDirector> resultados = new ArrayList<>();

        // Revisamos cada director
        for (Director d : directores.values()) {

            //Para el director creamos una lista que contenga todas las calificaciones de sus películas
            ArrayList<Double> todasLasNotas = new ArrayList<>();

            //Contador de cantidad de películas
            int cantidadPeliculas = 0;

            //Revisamos todas las películas del director (las keys de su hashtable son las id de las peliculas, que es lo único que necesitamos)
            for (NotNullInteger idPelicula : d.getDirectedMovies().keys()) {
                int reviewsPorPelicula = 0;

                //En la lista de reviews buscamos todas las reviews asociadas a la película en cuestion
                for (Review r : reviews) {
                    if (r.getPelicula().getId().equals(idPelicula)) {
                        todasLasNotas.add(r.getEvaluation()); //agregamos a la lista de notas la evaluacion de esta review
                        reviewsPorPelicula++;
                    }
                }

                // Aumentamos la cantidad de películas 1 sola vez si para esat película encontramos al menos 1 review
                if (reviewsPorPelicula > 0) {
                    cantidadPeliculas++;
                }
            }

            //Filtro
            if (cantidadPeliculas > 1 && todasLasNotas.size() > 100) {
                //Ordenamos la lista de todas las notas para hallar la mediana (el valor en medio de la lista ordenada)
                ordenarTodasLasNotas(todasLasNotas);
                double mediana;
                int n = todasLasNotas.size();
                if (n % 2 == 0) {
                    mediana = (todasLasNotas.get(n / 2 - 1) + todasLasNotas.get(n / 2)) / 2.0;
                } else {
                    mediana = todasLasNotas.get(n / 2);
                }
                resultados.add(new ResultadoDirector(d.getName().getValue(), cantidadPeliculas, mediana));
            }
        }

        //La lista resultados tiene clases del tipo ResultadoDirector, que tiene todos los datos que necesitamos imprimir de cada director
        //Esta clase implementa Comparable, y en su metodo compareTo() compara en funcion a la mediana que es lo que queremos
        ordenarPorMedianaDescendente(resultados);

        for (int i = 0; i < Math.min(10, resultados.size()); i++) {
            ResultadoDirector r = resultados.get(i);
            System.out.println(r.getNombre() + "," + r.getCantidadPeliculas() + "," + r.getMediana());
        }
    }

    private static void ordenarPorMedianaDescendente(ArrayList<ResultadoDirector> lista) {
        QuickSort<ResultadoDirector> sortQ = new QuickSort<>();
        sortQ.quickSort(lista);
    }

    private static void ordenarTodasLasNotas(ArrayList<Double> todasLasNotas) {
        QuickSort<Double> sortQ = new QuickSort<>();
        sortQ.quickSort(todasLasNotas);
    }
}

