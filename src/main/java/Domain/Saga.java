package Domain;

import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.SimpleLinkedList.MyLinkedList;
public class Saga {
    private NotNullInteger id;
    private NotBlankString title;
    private MyLinkedList<Pelicula> movies;

    //Complete Constructor
    public Saga(NotNullInteger id, NotBlankString title) {
        this.id = id;
        this.title = title;
        this.movies = new MyLinkedList<>();
    }

    //Getters and Setters
    public NotNullInteger getId() {
        return id;
    }

    public NotBlankString getTitle() {
        return title;
    }


    public MyLinkedList<Pelicula> getMovies() {
        return movies;
    }
    public void agregarPelicula(Pelicula pelicula) {
        this.movies.add(pelicula);
    }
}
