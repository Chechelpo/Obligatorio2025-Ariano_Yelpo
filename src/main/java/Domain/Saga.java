package Domain;

import Interfaces.MyList;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.SimpleLinkedList.MyLinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Saga {
    private NotNullInteger id;
    private NotBlankString title;
    private LinkedList<Pelicula> movies;

    //Complete Constructor
    public Saga(NotNullInteger id, NotBlankString title) {
        this.id = id;
        this.title = title;
        this.movies = new LinkedList<Pelicula>();
    }

    //Getters and Setters
    public NotNullInteger getId() {
        return id;
    }

    public NotBlankString getTitle() {
        return title;
    }

    public void setTitle(NotBlankString title) {
        this.title = title;
    }

    public LinkedList<Pelicula> getMovies() {
        return movies;
    }
    public void agregarPelicula(Pelicula pelicula) {
        this.movies.add(pelicula);
    }
}
