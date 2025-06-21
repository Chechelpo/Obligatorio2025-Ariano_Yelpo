package Services.Data.Loaders;

import Domain.Pelicula;
import Domain.Saga;
import Interfaces.MyHashTable;
import Semantics.NotBlankString;
import Semantics.NotNullInteger;
import Utils.HashTableCerrado.HashCerrado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DSLTest {

    @Test
    void start() {
        DSL dsl = new DSL();
        dsl.start();

        MyHashTable<NotNullInteger, Pelicula> peliculasPorID = dsl.getPeliculasPorID();
        HashCerrado<NotNullInteger, Saga> sagasPorID = dsl.getSagasPorID();

        assertEquals(new NotBlankString("Jumanji"),peliculasPorID.get(new NotNullInteger(8844)).getTitle());

        assertEquals(new NotBlankString("Grumpy Old Men Collection"),sagasPorID.get(new NotNullInteger(119050)).getTitle());
    }
}