package Services.Data.Loaders;

import Domain.Pelicula;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Services.Data.managers.PeliculaManager;
import Services.Data.managers.SagaManager;
import Services.Data.managers.GeneroManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

class PeliculaLoader {
    private SagaManager sagaManager;
    private GeneroManager generoManager;
    private PeliculaManager peliculaManager;

    public PeliculaLoader() {
        this.sagaManager = new SagaManager();
        this.generoManager = new GeneroManager();
        this.peliculaManager = new PeliculaManager();
    }

    public SagaManager getSagaManager() {
        return sagaManager;
    }

    public GeneroManager getGeneroManager() {
        return generoManager;
    }

    public PeliculaManager getPeliculaManager() {
        return peliculaManager;
    }

    public void cargarPeliculas(InputStream csvStream) {
        CSVFormat format = CSVFormat.Builder.create()
                .setHeader()
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();

        try (
                Reader reader = new InputStreamReader(csvStream, StandardCharsets.UTF_8);
                CSVParser parser = new CSVParser(reader, format)
        ) {
            for (CSVRecord record : parser) {
                try {
                    // Campos base
                    String idStr = record.get("id");
                    String titleStr = record.get("title");
                    String budgetStr = record.get("budget");
                    String langStr = record.get("original_language");
                    String revenueStr = record.get("revenue");

                    if (idStr == null || idStr.isBlank() || titleStr == null || titleStr.isBlank())
                        continue;

                    // Transformamos las entries al formato de la pelicula
                    NotNullInteger id = new NotNullInteger(Integer.parseInt(idStr.trim()));
                    NotBlankString title = new NotBlankString(titleStr.trim());
                    NotBlankString lang = new NotBlankString(langStr.trim());
                    int budget = parseSafeInt(budgetStr);
                    int revenue = parseSafeInt(revenueStr);

                    // Se encarga de buscar y/o añadir la nueva película
                    Pelicula pelicula = peliculaManager.managePelicula(id, title, budget, lang, revenue);

                    // Registrar saga (puede generar una unitaria si no hay JSON válido)
                    String belongsToCollection = record.get("belongs_to_collection");
                    sagaManager.registrarSaga(pelicula, belongsToCollection);

                    // Registrar géneros (pueden ser múltiples)
                    String genresRaw = record.get("genres");
                    generoManager.registrarGeneros(pelicula, genresRaw);

                } catch (NumberFormatException ignored) {
                } catch (Exception e) {
                    System.err.println("Error procesando la película de titulo : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo de películas: " + e.getMessage());
        }
    }


    private int parseSafeInt(String value) {
        try {
            return (value == null || value.isBlank()) ? 0 : Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
