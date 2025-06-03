package Domain;

import Semantics.NotBlankString;

import java.util.List;

public class Saga {
    private int id;
    private NotBlankString title;
    private List<Pelicula> movies;

    //Empty Constructor
    public Saga() {
    }

    //Complete Constructor
    public Saga(int id, NotBlankString title, List<Pelicula> movies) {
        this.id = id;
        this.title = title;
        this.movies = movies;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotBlankString getTitle() {
        return title;
    }

    public void setTitle(NotBlankString title) {
        this.title = title;
    }

    public List<Pelicula> getMovies() {
        return movies;
    }

    public void setMovies(List<Pelicula> movies) {
        this.movies = movies;
    }
}
