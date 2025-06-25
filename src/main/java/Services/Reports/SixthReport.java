package Services.Reports;

import Domain.GeneroConConteo;
import Domain.Pelicula;
import Domain.Review;
import Domain.Usuario;
import Interfaces.HashCerrado;
import Interfaces.MyHashTable;
import Interfaces.MyList;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;
import Utils.HashTableCerrado.MyHashCerrado;
import Utils.QuickSort.QuickSort;
import Utils.SimpleLinkedList.MyLinkedList;

import java.util.ArrayList;

public class SixthReport {

    public static void sixthReport(MyList<Review> reviews) {

        // Paso 1: Recolección de estadísticas por género y usuario

        //Hash para guardar la cantidad de reviews por género (para luego ver cual es el top 10  géneros mas vistos)
        HashCerrado<String, Integer> conteoGlobalPorGenero = new MyHashCerrado<>(100);

        //Hash anidado cuya clave es el género, y el valor es otro hash cuya clave es la id del usuario y el valor es la cantridad de reviews del usuario sobre ese genero
        HashCerrado<String, HashCerrado<NotNullInteger, Integer>> conteoPorGeneroYUsuario = new MyHashCerrado<>(100);

        // Recorremos reviews
        for (Review review : reviews) {

            //obtenemos id del usuario y la película
            NotNullInteger userId = review.getUsuario().getId();
            Pelicula pelicula = review.getPelicula();

            //para cada genero de la pelicula
            for (String genero : pelicula.getGenero().keys()) {

                // Incrementar conteo global del género
                int total = conteoGlobalPorGenero.get(genero) != null ? conteoGlobalPorGenero.get(genero) : 0;
                conteoGlobalPorGenero.put(genero, total + 1);

                // Incrementar conteo de usuario para ese género
                // si es la primera vez qe vemos este genero, entonces agregamos un hash asociado a este genero al hash conteoPorGeneroYUsuario
                if (!conteoPorGeneroYUsuario.containsKey(genero)) {
                    conteoPorGeneroYUsuario.put(genero, new MyHashCerrado<>(100));
                }

                HashCerrado<NotNullInteger, Integer> usuariosPorGenero = conteoPorGeneroYUsuario.get(genero);
                int actual = usuariosPorGenero.get(userId) != null ? usuariosPorGenero.get(userId) : 0;
                usuariosPorGenero.put(userId, actual + 1);
            }
        }

        // Paso 2: Obtener top 10 géneros más evaluados

        ArrayList<GeneroConConteo> generos = new ArrayList<>();
        for (String genero : conteoGlobalPorGenero.keys()) {
            generos.add(new GeneroConConteo(genero, conteoGlobalPorGenero.get(genero)));
        }

        //Hacemos esto porque nuestro quicksort es para arraylists
        QuickSort<GeneroConConteo> sort = new QuickSort<>();
        sort.quickSort(generos);

        //esto es por si hay menos de 10 géneros (cosa que no va a pasar en este caso)
        int top = Math.min(10, generos.size());

        // Paso 3: Para cada género del top, encontrar el usuario top
        // Si top es menos de 10, de eso ya se encarga top

        for (int i = 0; i < top; i++) {
            String genero = generos.get(i).getGenero();

            //obtenemos el hash asociado a esteghenro que tiene todos los usuarios junto con su cantidad de reviews de este genero
            HashCerrado<NotNullInteger, Integer> usuarios = conteoPorGeneroYUsuario.get(genero);

            NotNullInteger mejorUsuario = null;
            int max = -1;

            // Recorremos todos los usuarios que evaluaron ese género
            //Guardamos el que tiene más evaluaciones

            for (NotNullInteger userId : usuarios.keys()) {
                int cant = usuarios.get(userId);
                if (cant > max) {
                    mejorUsuario = userId;
                    max = cant;
                }
            }

            //print
            if (mejorUsuario != null) {
                System.out.println(mejorUsuario + "," + genero + "," + max);
            }
        }
    }
}

