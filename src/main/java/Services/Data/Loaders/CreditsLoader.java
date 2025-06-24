// Turbo CreditsLoader.java sin hilos (versión secuencial optimizada)
package Services.Data.Loaders;

import Domain.Actor;
import Domain.Director;
import Domain.Pelicula;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Services.Data.managers.PeliculaManager;
import Utils.HashTable.HashTable;
import Interfaces.MyHashTable;
import Utils.HashTableCerrado.MyHashCerrado;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class CreditsLoader {
    private final PeliculaManager peliculaManager;
    private final MyHashCerrado<NotNullInteger, Director> directores = new MyHashCerrado<>(20000);
    private final MyHashTable<NotNullInteger, Actor> actores = new HashTable<>(100000);

    public CreditsLoader(PeliculaManager peliculaManager) {
        this.peliculaManager = peliculaManager;
    }

    public PeliculaManager getPeliculaManager() {
        return peliculaManager;
    }

    public void cargarCredits(InputStream csvStream) {
        CSVFormat format = CSVFormat.Builder.create()
                .setHeader()
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();

        try (
                Reader reader = new InputStreamReader(csvStream, StandardCharsets.UTF_8);
                CSVParser parser = new CSVParser(reader, format)
        ) {
            int count = 0;
            for (CSVRecord record : parser) {
                try {
                    NotNullInteger peliculaId = new NotNullInteger(Integer.parseInt(record.get("id")));
                    Pelicula pelicula = peliculaManager.buscarPelicula(peliculaId);
                    if (pelicula == null) continue;

                    String rawCast = record.get("cast");
                    JSONArray castArray = new JSONArray(rawCast);

                    for (int i = 0; i < castArray.length(); i++) {
                        JSONObject actorJson = castArray.getJSONObject(i);
                        NotNullInteger actorId = new NotNullInteger(actorJson.getInt("id"));
                        NotBlankString actorName = new NotBlankString(actorJson.getString("name"));

                        Actor actor = actores.get(actorId);
                        if (actor == null) {
                            actor = new Actor(actorId, actorName);
                            actores.put(actorId, actor);
                        }
                        actor.agregarPelicula(pelicula);
                    }

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
                            break;
                        }
                    }

                    if (++count % 10000 == 0)
                        System.out.println("Credits cargados: " + count);

                } catch (Exception ignored) {
                }
            }

        } catch (Exception e) {
            System.err.println("Error al cargar credits.csv: " + e.getMessage());
        }
    }

    public MyHashCerrado<NotNullInteger, Director> getDirectores() {
        return directores;
    }

    public MyHashTable<NotNullInteger, Actor> getActores() {
        return actores;
    }
}
