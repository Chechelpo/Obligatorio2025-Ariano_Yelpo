package Interfaces;

public interface HashCerrado<K, V> {
    void put(K clave, V valor);
    V get(K clave);
    boolean containsKey(K clave);
    void remove(K clave);
    Iterable<V> values();         // Para iterar por valores
    Iterable<K> keys();           // Opcional: para iterar claves
    int size();                   // Cantidad de elementos reales
}
