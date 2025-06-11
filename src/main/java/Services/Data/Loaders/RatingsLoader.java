package Services.Data.Loaders;

import Domain.Pelicula;
import Domain.Review;
import Domain.Usuario;
import Semantics.NotNullInteger;
import Services.Data.managers.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

class RatingsLoader {
    UserManager userManager;
    PeliculaManager peliculaManager;
    ReviewManager reviewManager;

    public RatingsLoader(PeliculaManager peliculaManager) {
        this.userManager = new UserManager();
        this.peliculaManager = peliculaManager;
        this.reviewManager = new ReviewManager();
    }
    public void cargarRatings(String pathCsv)
    {
        CSVFormat format = CSVFormat.Builder.create()
                .setHeader()
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();

        try (
                Reader reader = new FileReader(pathCsv);
                CSVParser parser = new CSVParser(reader, format)
        ) {
            for (CSVRecord record : parser) {
                try {
                    NotNullInteger userId = new NotNullInteger(Integer.parseInt(record.get("userId")));
                    NotNullInteger movieId = new NotNullInteger(Integer.parseInt(record.get("movieId")));
                    double rating = Double.parseDouble(record.get("rating"));
                    long timestamp = Long.parseLong(record.get("timestamp"));


                    //Buscamos si esta el usuario, la funcion se encarga de crearlo si no esta registrado todavia
                    Usuario user = userManager.getUserByID(userId);
                    //Buscamos las peliculas anteriores
                    Pelicula pelicula = peliculaManager.buscarPelicula(movieId);
                    if (pelicula == null) continue; // Si la película no existe, ignoramos

                    Review review = new Review(rating);

                    // Asignar review a usuario y película
                    user.addReview(review);
                    pelicula.getReviews().add(review); // o pelicula.agregarReview(review)

                } catch (Exception e) {
                    // Línea inválida, la ignoramos
                }
            }

        } catch (Exception e) {
            System.err.println("Error al cargar ratings: " + e.getMessage());
        }
    }
}
