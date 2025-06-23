package Services.Data.managers;

import Domain.Pelicula;
import Domain.Saga;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTableCerrado.HashCerrado;
import org.json.JSONObject;


public class SagaManager {
    private final HashCerrado<NotNullInteger, Saga> sagas;
    public SagaManager() {
        sagas = new HashCerrado<>(45000);
    }

    public HashCerrado<NotNullInteger, Saga> getSagas() {
        return sagas;
    }

    public void registrarSaga(Pelicula pelicula, String rawJson) {
        if (rawJson == null || rawJson.isBlank() || rawJson.equals("null")) {
            crearSagaUnitaria(pelicula);
            return;
        }

        try {
            JSONObject obj = new JSONObject(rawJson);
            NotNullInteger sagaId = new NotNullInteger(obj.getInt("id"));
            NotBlankString sagaName = new NotBlankString(obj.getString("name"));

            Saga saga = sagas.get(sagaId);
            if (saga == null) {
                saga = new Saga(sagaId, sagaName);
                sagas.put(sagaId, saga);
            }
            saga.agregarPelicula(pelicula);

        } catch (Exception e) {
            // JSON mal formado, usar saga unitaria
            crearSagaUnitaria(pelicula);
        }
    }

    private void crearSagaUnitaria(Pelicula pelicula) {
        NotNullInteger id = pelicula.getId();
        NotBlankString titulo = pelicula.getTitle();

        Saga saga = new Saga(id, titulo);
        saga.agregarPelicula(pelicula);
        sagas.put(id, saga);
    }

}
