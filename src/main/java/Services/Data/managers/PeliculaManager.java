package Services.Data.managers;

import Domain.Pelicula;
import Domain.Review;
import Interfaces.MyHashTable;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;


public class PeliculaManager {
    private final MyHashTable<NotNullInteger, Pelicula> peliculas = new HashTable<>();

    public Pelicula buscarPelicula(NotNullInteger id) {
        return peliculas.get(id);
    }
    private void registrarPelicula(NotNullInteger id,Pelicula pelicula) {
        peliculas.put(id,pelicula);
    }
    public void addReviewToMovie(NotNullInteger MovieID, Review review) {
        peliculas.get(MovieID).addReview(review);
    }
    public Pelicula managePelicula(NotNullInteger id,NotBlankString titulo, int budget, NotBlankString langStr, int revenue){
        Pelicula toADD = buscarPelicula(id);
        if(toADD == null){
            toADD = new Pelicula(id,titulo,budget,langStr,revenue,revenue-budget);
            registrarPelicula(id, toADD);
        } return toADD;
    }

    public MyHashTable<NotNullInteger, Pelicula> getPeliculas() {
        return peliculas;
    }
}
