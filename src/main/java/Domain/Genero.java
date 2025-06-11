package Domain;

import Semantics.NotBlankString;
import Semantics.NotNullInteger;

import java.util.List;

public class Genero {
    private NotNullInteger id;
    private NotBlankString name;
    private List<Pelicula> peliculas; //????
    private int popularity;

    //Empty Constructor
    public Genero() {
    }

    //Complete Constructor
    public Genero(NotNullInteger id, NotBlankString name, int popularity) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
    }

    //Getters and Setters
    public NotNullInteger getId() {
        return id;
    }

    public void setId(NotNullInteger id) {
        this.id = id;
    }

    public NotBlankString getName() {
        return name;
    }

    public void setName(NotBlankString name) {
        this.name = name;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void agregarPelicula(Pelicula pelicula) {
        this.peliculas.add(pelicula);
    }
}
