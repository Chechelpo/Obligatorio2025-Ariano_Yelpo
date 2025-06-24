package Domain;

import Interfaces.MyHashTable;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;


public class Director {
    private NotNullInteger id;
    private MyHashTable<NotNullInteger,Boolean> directedMovies;
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

    public MyHashTable<NotNullInteger,Boolean> getDirectedMovies() {
        return directedMovies;
    }

    public NotBlankString getName() {
        return name;
    }


    public void agregarPelicula(Pelicula pelicula) {
        this.directedMovies.put(pelicula.getId(),true);
    }
}
