package Services.Data.Loaders;

import Domain.Pelicula;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Services.Data.managers.SagaManager;
import Services.Data.managers.GeneroManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class PeliculaLoader {

    public List<Pelicula> cargarPeliculas(String pathCsv, SagaManager sagaManager, GeneroManager generoManager) {
        List<Pelicula> peliculas = new ArrayList<>();

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
                    // Campos base
                    String idStr = record.get("id");
                    String titleStr = record.get("title");
                    String budgetStr = record.get("budget");
                    String langStr = record.get("original_language");
                    String revenueStr = record.get("revenue");

                    if (idStr == null || idStr.isBlank() || titleStr == null || titleStr.isBlank())
                        continue;

                    int id = Integer.parseInt(idStr.trim());
                    int budget = parseSafeInt(budgetStr);
                    int revenue = parseSafeInt(revenueStr);

                    Pelicula pelicula = new Pelicula(
                            new NotNullInteger(id),
                            new NotBlankString(titleStr),
                            budget,
                            new NotBlankString(langStr != null ? langStr : "unknown"),
                            revenue,
                            revenue - budget
                    );

                    // Registrar saga (puede generar una unitaria si no hay JSON válido)
                    String belongsToCollection = record.get("belongs_to_collection");
                    sagaManager.registrarSaga(pelicula, belongsToCollection);

                    // Registrar géneros (pueden ser múltiples)
                    String genresRaw = record.get("genres");
                    generoManager.registrarGeneros(pelicula, genresRaw);

                    peliculas.add(pelicula);

                } catch (NumberFormatException e) {
                    // ID no válido u otro número mal formado → ignorar
                } catch (Exception e) {
                    System.err.println("Error procesando una película: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Error al leer el archivo de películas: " + e.getMessage());
        }

        return peliculas;
    }

    private int parseSafeInt(String value) {
        try {
            return (value == null || value.isBlank()) ? 0 : Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
