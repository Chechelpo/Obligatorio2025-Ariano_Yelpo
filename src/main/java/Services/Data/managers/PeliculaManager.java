package Services.Data.managers;

import Domain.Pelicula;
import Interfaces.MyHashTable;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;


public class PeliculaManager {
    private final MyHashTable<NotNullInteger, Pelicula> peliculas = new HashTable<>(49000);

    public Pelicula buscarPelicula(NotNullInteger id) {
        return peliculas.get(id);
    }
    private void registrarPelicula(NotNullInteger id,Pelicula pelicula) {
        peliculas.put(id,pelicula);
    }
    public Pelicula managePelicula(NotNullInteger id,NotBlankString titulo, int budget, NotBlankString langStr, int revenue){
        Pelicula toADD = buscarPelicula(id);
        if(toADD == null){
            toADD = new Pelicula(id,titulo,budget,langStr,revenue);
            registrarPelicula(id, toADD);
        } return toADD;
    }

    public MyHashTable<NotNullInteger, Pelicula> getPeliculas() {
        return peliculas;
    }
}
