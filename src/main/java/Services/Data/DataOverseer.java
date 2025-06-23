package Services.Data;

import Domain.Director;
import Domain.Pelicula;
import Domain.Saga;
import Interfaces.HashCerrado;
import Interfaces.MyHashTable;
import Semantics.NotNullInteger;
import Services.Data.Loaders.DSL;

public class DataOverseer {
    final DSL dataServiceLoader = new DSL();
    private final MyHashTable<NotNullInteger, Pelicula> peliculasPorId;
    private final HashCerrado<NotNullInteger, Saga> sagasPorID;
    private final HashCerrado<NotNullInteger, Director> directorPorID;

    DataOverseer() {
        this.peliculasPorId = dataServiceLoader.getPeliculasPorID();
        this.sagasPorID= dataServiceLoader.getSagasPorID();
        this.directorPorID = dataServiceLoader.getDirectors();
    }
}
