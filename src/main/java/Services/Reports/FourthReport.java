package Services.Reports;

import Domain.Director;
import Interfaces.HashCerrado;
import Semantics.NotNullInteger;
import Domain.Review;
import Interfaces.MyList;
import Utils.HashTableCerrado.MyHashCerrado;

import java.util.ArrayList;
import java.util.Collections;

public class FourthReport {
    public static void fourthReport(HashCerrado<NotNullInteger, Director> directores, MyList<Review> reviews) {
        HashCerrado<String, ArrayList<Double>> calificacionesPorDirector = new MyHashCerrado<>(50);
        HashCerrado<String, Integer> cantidadPeliculasPorDirector = new MyHashCerrado<>(50);

        // Mapear id_pelicula → calificaciones (para eficiencia)
        HashCerrado<Integer, ArrayList<Double>> calificacionesPorPelicula = new MyHashCerrado<>(1000);
        for (Review r : reviews) {
            int id = r.getPelicula().getId().getValue();
            if (!calificacionesPorPelicula.containsKey(id)) {
                calificacionesPorPelicula.put(id, new ArrayList<>());
            }
            calificacionesPorPelicula.get(id).add(r.getEvaluation());
        }

        // Para cada director, juntar calificaciones de sus películas
        for (Director d : directores) {
            ArrayList<Double> todasLasNotas = new ArrayList<>();
            int cantidadPeliculas = 0;
            int totalReviews = 0;

            for (NotNullInteger idPelicula : d.getDirectedMovies().keys()) {
                ArrayList<Double> calificaciones = calificacionesPorPelicula.get(idPelicula.getValue());
                if (calificaciones != null && !calificaciones.isEmpty()) {
                    todasLasNotas.addAll(calificaciones);
                    totalReviews += calificaciones.size();
                    cantidadPeliculas++;
                }
            }

            if (cantidadPeliculas > 1 && totalReviews > 100) {
                calificacionesPorDirector.put(d.getName().getValue(), todasLasNotas);
                cantidadPeliculasPorDirector.put(d.getName().getValue(), cantidadPeliculas);
            }
        }

        // Crear lista ordenada por mediana
        ArrayList<ResultadoDirector> resultados = new ArrayList<>();

        for (String nombre : calificacionesPorDirector.keys()) {
            ArrayList<Double> notas = calificacionesPorDirector.get(nombre);
            Collections.sort(notas);
            double mediana;
            int n = notas.size();
            if (n % 2 == 0) {
                mediana = (notas.get(n / 2 - 1) + notas.get(n / 2)) / 2.0;
            } else {
                mediana = notas.get(n / 2);
            }
            int cantidadPeliculas = cantidadPeliculasPorDirector.get(nombre);
            resultados.add(new ResultadoDirector(nombre, cantidadPeliculas, mediana));
        }

        resultados.sort((a, b) -> Double.compare(b.mediana, a.mediana));

        // Imprimir Top 10
        for (int i = 0; i < Math.min(10, resultados.size()); i++) {
            ResultadoDirector r = resultados.get(i);
            System.out.println(r.nombre + "," + r.cantidadPeliculas + "," + r.mediana);
        }
    }

    static class ResultadoDirector {
        String nombre;
        int cantidadPeliculas;
        double mediana;

        public ResultadoDirector(String nombre, int cantidadPeliculas, double mediana) {
            this.nombre = nombre;
            this.cantidadPeliculas = cantidadPeliculas;
            this.mediana = mediana;
        }
    }
}
