package Services.Reports;

import Domain.Director;
import Domain.Review;
import Interfaces.HashCerrado;
import Interfaces.MyList;
import Semantics.NotNullInteger;
import Utils.QuickSort.QuickSort;
import Utils.ResultadoDirector;
import Utils.HashTableCerrado.MyHashCerrado;

import java.util.ArrayList;

public class FourthReport {

    public static void fourthReport(HashCerrado<NotNullInteger, Director> directorPorID,
                                    MyList<Review> reviews) {

        ArrayList<ResultadoDirector> resultados = new ArrayList<>();

        // Paso 1: Indexar todas las reviews por idPelicula
        HashCerrado<NotNullInteger, ArrayList<Double>> calificacionesPorPelicula = new MyHashCerrado<>(10000);

        for (Review r : reviews) {
            NotNullInteger idPelicula = r.getPelicula().getId();
            if (!calificacionesPorPelicula.containsKey(idPelicula)) {
                calificacionesPorPelicula.put(idPelicula, new ArrayList<>());
            }
            calificacionesPorPelicula.get(idPelicula).add(r.getEvaluation());
        }

        // Paso 2: Procesar cada director y calcular su mediana
        for (Director d : directorPorID.values()) {
            ArrayList<Double> todasLasNotas = new ArrayList<>();
            int cantidadPeliculas = 0;

            for (NotNullInteger idPelicula : d.getDirectedMovies().keys()) {
                ArrayList<Double> notas = calificacionesPorPelicula.get(idPelicula);
                if (notas != null && !notas.isEmpty()) {
                    todasLasNotas.addAll(notas);
                    cantidadPeliculas++;
                }
            }

            // Filtro por condiciones de la letra
            if (cantidadPeliculas > 1 && todasLasNotas.size() > 100) {
                ordenarTodasLasNotas(todasLasNotas);
                double mediana = calcularMediana(todasLasNotas);
                resultados.add(new ResultadoDirector(d.getName().getValue(), cantidadPeliculas, mediana));
            }
        }

        // Paso 3: Ordenar por mediana descendente y mostrar top 10
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

    private static double calcularMediana(ArrayList<Double> lista) {
        int n = lista.size();
        if (n % 2 == 0) {
            return (lista.get(n / 2 - 1) + lista.get(n / 2)) / 2.0;
        } else {
            return lista.get(n / 2);
        }
    }
}


