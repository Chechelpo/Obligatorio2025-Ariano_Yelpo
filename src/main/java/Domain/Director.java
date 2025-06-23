package Domain;

import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;

import java.util.List;

public class Director {
    private NotNullInteger id;
    private HashTable<NotNullInteger,Boolean> directedMovies;
    private NotBlankString name;

    //Empty Constructor
    public Director() {
    }

    //Complete Constructor
    public Director(NotNullInteger id, NotBlankString name) {
        this.id = id;
        this.name = name;
        this.directedMovies = new HashTable<>();
    }

    //Getters and Setters

    public NotNullInteger getId() {
        return id;
    }

    public void setId(NotNullInteger id) {
        this.id = id;
    }

    public HashTable<NotNullInteger,Boolean> getDirectedMovies() {
        return directedMovies;
    }

    public NotBlankString getName() {
        return name;
    }

    public void setName(NotBlankString name) {
        this.name = name;
    }
    public void agregarPelicula(Pelicula pelicula) {
        this.directedMovies.put(pelicula.getId(),true);
    }
}
