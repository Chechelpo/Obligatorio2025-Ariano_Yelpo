package Domain;

import Semantics.NotBlankString;

import java.util.List;

public class Director {
    private List<Pelicula> directedMovies;
    private NotBlankString name;

    //Empty Constructor
    public Director() {
    }

    //Complete Constructor
    public Director(List<Pelicula> directedMovies, NotBlankString name) {
        this.directedMovies = directedMovies;
        this.name = name;
    }

    //Getters and Setters
    public List<Pelicula> getDirectedMovies() {
        return directedMovies;
    }

    public void setDirectedMovies(List<Pelicula> directedMovies) {
        this.directedMovies = directedMovies;
    }

    public NotBlankString getName() {
        return name;
    }

    public void setName(NotBlankString name) {
        this.name = name;
    }
    public void agregarPelicula(Pelicula pelicula) {
        this.directedMovies.add(pelicula);
    }
}
