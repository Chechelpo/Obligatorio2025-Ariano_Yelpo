package Services.Data;

import Domain.Director;
import Domain.Pelicula;
import Domain.Review;
import Domain.Saga;
import Interfaces.HashCerrado;
import Interfaces.MyHashTable;
import Interfaces.MyList;
import Semantics.NotNullInteger;
import Services.Data.Loaders.DSL;

public class DataOverseer {
    final DSL dataServiceLoader = new DSL();
    private final MyHashTable<NotNullInteger, Pelicula> peliculasPorId;
    private final HashCerrado<NotNullInteger, Saga> sagasPorID;
    private final HashCerrado<NotNullInteger, Director> directorPorID;
    private final MyList<Review> reviews;
    private final HashCerrado<String,Boolean> generos;

    public DataOverseer() {
        dataServiceLoader.start();
        this.peliculasPorId = dataServiceLoader.getPeliculasPorID();
        this.sagasPorID= dataServiceLoader.getSagasPorID();
        this.directorPorID = dataServiceLoader.getDirectors();
        this.reviews = dataServiceLoader.getReviews();
        this.generos = dataServiceLoader.getGeneros();
    }

    public MyHashTable<NotNullInteger, Pelicula> getPeliculasPorId() {
        return peliculasPorId;
    }

    public HashCerrado<String, Boolean> getGeneros() {
        return generos;
    }

    public HashCerrado<NotNullInteger, Saga> getSagasPorID() {
        return sagasPorID;
    }

    public HashCerrado<NotNullInteger, Director> getDirectorPorID() {
        return directorPorID;
    }

    public MyList<Review> getReviews() {
        return reviews;
    }
}
