package Services.Data.Loaders;

import Domain.Director;
import Domain.Pelicula;
import Domain.Review;
import Domain.Saga;
import Interfaces.MyHashTable;
import Interfaces.MyList;
import Semantics.NotNullInteger;
import Services.Data.managers.PeliculaManager;
import Utils.HashTableCerrado.MyHashCerrado;

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
    public MyHashCerrado<NotNullInteger, Director> getDirectors() {
        return creditsLoader.getDirectores();
    }
    public MyHashTable<NotNullInteger, Pelicula> getPeliculasPorID() {
        return peliculaLoader.getPeliculaManager().getPeliculas();
    }

    public MyHashCerrado<NotNullInteger, Saga> getSagasPorID() {
        return peliculaLoader.getSagaManager().getSagas();
    }

    public RatingsLoader getRatingsLoader() {
        return ratingsLoader;
    }
    public MyHashCerrado<String,Boolean> getGeneros(){
        return peliculaLoader.getGeneroManager().getGeneros();
    }

    public MyList<Review> getReviews() {
        return ratingsLoader.reviews;
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
        long inicio = System.currentTimeMillis();
        InputStream csvStream = getResourceStream(MOVIES_METADATA_CSV);
        peliculaLoader.cargarPeliculas(csvStream);
        System.out.println("Finished loading movies.csv");
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución de la carga de movies.csv: " + (fin - inicio) + " ms");
    }

    private void secondPhase(PeliculaManager peliculaManager) {
        long inicio = System.currentTimeMillis();
        RatingsLoader ratingsLoader = new RatingsLoader(peliculaManager);
        this.setRatingsLoader(ratingsLoader);
        InputStream csvStream = getResourceStream(RATINGS_CSV);
        ratingsLoader.cargarRatings(csvStream);
        System.out.println("Finished loading ratings.csv");
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución de la carga de ratings.csv: " + (fin - inicio) + " ms");
    }

    private void thirdPhase(PeliculaManager peliculaManager) {
        long inicio = System.currentTimeMillis();
        CreditsLoader creditsLoader = new CreditsLoader(peliculaManager);
        this.setCreditsLoader(creditsLoader);
        InputStream csvStream = getResourceStream(CREDITS_CSV);
        creditsLoader.cargarCredits(csvStream);
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución de la carga de credits.csv: " + (fin - inicio) + " ms");
    }

    public void start() {
        long inicio = System.currentTimeMillis();
        firstPhase();
        secondPhase(peliculaLoader.getPeliculaManager());
        thirdPhase(peliculaLoader.getPeliculaManager());
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total de ejecucion de carga de datos " + (fin - inicio) + " ms");
    }
}
