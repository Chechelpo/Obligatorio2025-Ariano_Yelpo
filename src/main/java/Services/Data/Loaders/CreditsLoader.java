package Services.Data.Loaders;

import Domain.Director;
import Domain.Pelicula;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Services.Data.managers.PeliculaManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.Reader;

public class CreditsLoader {
/*    private PeliculaManager peliculaManager;

    public CreditsLoader(PeliculaManager peliculaManager) {
        this.peliculaManager = new PeliculaManager();
    }

    public void cargarCredits(String pathCsv) {
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
                    NotNullInteger peliculaId = new NotNullInteger(Integer.parseInt(record.get("id")));
                    Pelicula pelicula = peliculaManager.buscarPelicula(peliculaId);
                    if (pelicula == null) continue;

                    // ---------- Actores ----------
                    String rawCast = record.get("cast");
                    JSONArray castArray = new JSONArray(rawCast);

                    for (int i = 0; i < castArray.length(); i++) {
                        JSONObject actorJson = castArray.getJSONObject(i);
                        NotNullInteger actorId = new NotNullInteger(actorJson.getInt("id"));
                        NotBlankString actorName = new NotBlankString(actorJson.getString("name"));


                    }

                    // ---------- Director ----------
                    String rawCrew = record.get("crew");
                    JSONArray crewArray = new JSONArray(rawCrew);

                    for (int i = 0; i < crewArray.length(); i++) {
                        JSONObject person = crewArray.getJSONObject(i);
                        if (person.getString("job").equalsIgnoreCase("Director")) {
                            NotNullInteger dirId = new NotNullInteger(person.getInt("id"));
                            NotBlankString dirName = new NotBlankString(person.getString("name"));

                            Director director = directores.get(dirId);
                            if (director == null) {
                                director = new Director(dirId, dirName);
                                directores.put(dirId, director);
                            }

                            director.agregarPelicula(pelicula);
                            break; // Solo 1 director principal
                        }
                    }

                } catch (Exception e) {
                    // Registro inválido → ignorar
                }
            }

        } catch (Exception e) {
            System.err.println("Error al cargar credits.csv: " + e.getMessage());
        }
    }

 */
}
