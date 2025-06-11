package Services.Data.managers;

import Domain.Genero;
import Domain.Pelicula;
import Domain.Saga;
import Interfaces.MyHashTable;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTable.HashTable;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeneroManager {
    private final MyHashTable<NotNullInteger, Genero> generos = new HashTable<>();

    public void registrarGeneros(Pelicula pelicula, String rawJson) {
        if (rawJson == null || rawJson.isBlank()) return;

        try {
            JSONArray array = new JSONArray(rawJson);
            for (int i = 0; i < array.length(); i++) {
                JSONObject g = array.getJSONObject(i);
                NotNullInteger id = new NotNullInteger(g.getInt("id"));
                NotBlankString nombre = new NotBlankString(g.getString("name"));

                Genero genero = generos.get(id);
                if (genero == null) {
                    genero = new Genero(id, nombre,0);
                    generos.put(id, genero);
                }

                genero.agregarPelicula(pelicula);
            }
        } catch (Exception e) {
            // JSON inválido: lo ignoramos
        }
    }

    public MyHashTable<NotNullInteger, Genero> getGeneros() {
        return generos;
    }
}
