package Services.Data.managers;

import Domain.Pelicula;
import Utils.HashTableCerrado.HashCerrado;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeneroManager {
    private final HashCerrado<String,Boolean> generos = new HashCerrado<>(5000);

    public void registrarGeneros(Pelicula pelicula, String rawJson) {
        if (rawJson == null || rawJson.isBlank()) return;

        try {
            JSONArray array = new JSONArray(rawJson);
            for (int i = 0; i < array.length(); i++) {
                JSONObject g = array.getJSONObject(i);
                String nombre = g.getString("name");
                if(!pelicula.getGenero().containsKey(nombre)) pelicula.addGenre(nombre);

                generos.put(nombre,true);
                pelicula.getGenero().put(nombre,true);
            }
        } catch (Exception e) {
            // JSON inválido: lo ignoramos
        }
    }

    public HashCerrado<String, Boolean> getGeneros() {
        return generos;
    }
}
