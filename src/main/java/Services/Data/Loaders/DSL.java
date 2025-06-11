package Services.Data.Loaders;

import Domain.Pelicula;
import Services.Data.managers.GeneroManager;
import Services.Data.managers.PeliculaManager;
import Services.Data.managers.SagaManager;

/*
El orden de carga es el siguiente:
MovieLoader -> RatingsLoader -> CreditsLoader
RatingsLoader precisa del SagaManager, GeneroManager resultado de MovieManager
CreditsLoader precisa de las peliculas en un hash para poder cargar los datos
 */
public class DSL {
    private PeliculaLoader peliculaLoader;
    private CreditsLoader creditsLoader;
    private RatingsLoader ratingsLoader;
    String pathToCSV;
    DSL(String pathToCSV) {
        this.pathToCSV = pathToCSV;
        this.peliculaLoader = new PeliculaLoader();
    }

    private void setCreditsLoader(CreditsLoader creditsLoader) {
        this.creditsLoader = creditsLoader;
    }

    private void setRatingsLoader(RatingsLoader ratingsLoader) {
        this.ratingsLoader = ratingsLoader;
    }

    private void firstPhase(){
        SagaManager sagaManager = new SagaManager();
        GeneroManager generoManager = new GeneroManager();
        peliculaLoader.cargarPeliculas(pathToCSV,sagaManager,generoManager);
    }
    private void secondPhase(PeliculaManager peliculaManager){
        RatingsLoader ratingsLoader = new RatingsLoader(peliculaLoader.getPeliculaManager());
        this.setRatingsLoader(ratingsLoader);
        ratingsLoader.cargarRatings(pathToCSV);
    }
    private void thirdPhase(PeliculaManager peliculaManager){
        CreditsLoader creditsLoader = new CreditsLoader(peliculaManager);
        this.setCreditsLoader(creditsLoader);
        creditsLoader.cargarCredits(pathToCSV);
    }
    public void Start(){
        firstPhase();
        secondPhase(peliculaLoader.getPeliculaManager());
        thirdPhase(peliculaLoader.getPeliculaManager());
    }
}
