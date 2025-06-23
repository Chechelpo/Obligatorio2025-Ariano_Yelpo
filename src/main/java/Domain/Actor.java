package Domain;

import Interfaces.MyHashTable;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;

public class Actor {
    private NotNullInteger id;
    private NotBlankString name;
    private final MyHashTable<NotNullInteger,Boolean> peliculas = new HashTable<>();

    public Actor(NotNullInteger id, NotBlankString name) {
        this.id = id;
        this.name = name;
    }
    public NotNullInteger getId() {
        return id;
    }
    public NotBlankString getName() {
        return name;
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.put(pelicula.getId(),true);
    }
}
