package Services.Data.Loaders;

import Domain.Pelicula;
import Domain.Saga;
import Interfaces.MyHashTable;
import Semantics.NotNullInteger;
import Services.Data.managers.PeliculaManager;
import Utils.HashTableCerrado.HashCerrado;

import java.io.InputStream;

public class DSL {
    private PeliculaLoader peliculaLoader;
    private CreditsLoader creditsLoader;
    private RatingsLoader ratingsLoader;

    private static final String MOVIES_METADATA_CSV = "csv/movies_metadata.csv";
    private static final String RATINGS_CSV = "csv/ratings_1mm.csv";
    private static final String CREDITS_CSV = "csv/credits.csv";

    public DSL() {
        this.peliculaLoader = new PeliculaLoader();
    }

    public MyHashTable<NotNullInteger, Pelicula> getPeliculasPorID() {
        return peliculaLoader.getPeliculaManager().getPeliculas();
    }

    public HashCerrado<NotNullInteger, Saga> getSagasPorID() {
        return peliculaLoader.getSagaManager().getSagas();
    }

    public RatingsLoader getRatingsLoader() {
        return ratingsLoader;
    }

    private void setCreditsLoader(CreditsLoader creditsLoader) {
        this.creditsLoader = creditsLoader;
    }

    private void setRatingsLoader(RatingsLoader ratingsLoader) {
        this.ratingsLoader = ratingsLoader;
    }

    private InputStream getResourceStream(String path) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (is == null) {
            throw new RuntimeException("No se pudo encontrar el archivo: " + path);
        }
        return is;
    }

    private void firstPhase() {
        InputStream csvStream = getResourceStream(MOVIES_METADATA_CSV);
        peliculaLoader.cargarPeliculas(csvStream);
    }

    private void secondPhase(PeliculaManager peliculaManager) {
        RatingsLoader ratingsLoader = new RatingsLoader(peliculaManager);
        this.setRatingsLoader(ratingsLoader);
        InputStream csvStream = getResourceStream(RATINGS_CSV);
        ratingsLoader.cargarRatings(csvStream);
    }

    private void thirdPhase(PeliculaManager peliculaManager) {
        /*CreditsLoader creditsLoader = new CreditsLoader(peliculaManager);
        this.setCreditsLoader(creditsLoader);
        InputStream csvStream = getResourceStream(CREDITS_CSV);
        creditsLoader.cargarCredits(csvStream);*/
    }

    public void start() {
        firstPhase();
        secondPhase(peliculaLoader.getPeliculaManager());
        // thirdPhase(peliculaLoader.getPeliculaManager());
    }
}
