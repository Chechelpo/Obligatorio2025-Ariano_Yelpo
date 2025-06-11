package Utils.HashTableCerrado;


import java.util.ArrayList;
import java.util.List;

public class HashCerrado<K, V> implements Interfaces.HashCerrado<K, V> {
    private static class Entrada<K, V> {
        K clave;
        V valor;
        boolean borrado;

        Entrada(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.borrado = false;
        }
    }

    private Entrada<K, V>[] tabla;
    private int size;
    private int capacidad;
    private static final double CARGA_MAXIMA = 0.6;

    @SuppressWarnings("unchecked")
    public HashCerrado(int capacidadInicial) {
        this.capacidad = capacidadInicial;
        this.tabla = (Entrada<K, V>[]) new Entrada[capacidad];
        this.size = 0;
    }

    public void put(K clave, V valor) {
        if ((double) size / capacidad >= CARGA_MAXIMA) {
            rehash();
        }

        int index = obtenerIndice(clave);
        while (tabla[index] != null && !tabla[index].borrado && !tabla[index].clave.equals(clave)) {
            index = (index + 1) % capacidad;
        }

        if (tabla[index] == null || tabla[index].borrado) {
            size++;
        }

        tabla[index] = new Entrada<>(clave, valor);
    }

    public V get(K clave) {
        int index = obtenerIndice(clave);
        int contador = 0;
        while (tabla[index] != null && contador < capacidad) {
            if (!tabla[index].borrado && tabla[index].clave.equals(clave)) {
                return tabla[index].valor;
            }
            index = (index + 1) % capacidad;
            contador++;
        }
        return null;
    }

    public boolean containsKey(K clave) {
        return get(clave) != null;
    }

    public void remove(K clave) {
        int index = obtenerIndice(clave);
        int contador = 0;
        while (tabla[index] != null && contador < capacidad) {
            if (!tabla[index].borrado && tabla[index].clave.equals(clave)) {
                tabla[index].borrado = true;
                size--;
                return;
            }
            index = (index + 1) % capacidad;
            contador++;
        }
    }

    public int size() {
        return size;
    }

    public Iterable<V> values() {
        List<V> lista = new ArrayList<>();
        for (Entrada<K, V> entrada : tabla) {
            if (entrada != null && !entrada.borrado) {
                lista.add(entrada.valor);
            }
        }
        return lista;
    }

    public Iterable<K> keys() {
        List<K> lista = new ArrayList<>();
        for (Entrada<K, V> entrada : tabla) {
            if (entrada != null && !entrada.borrado) {
                lista.add(entrada.clave);
            }
        }
        return lista;
    }

    private int obtenerIndice(K clave) {
        int hash = clave.hashCode();
        return Math.abs(hash) % capacidad;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Entrada<K, V>[] viejaTabla = tabla;
        capacidad *= 2;
        tabla = (Entrada<K, V>[]) new Entrada[capacidad];
        size = 0;

        for (Entrada<K, V> entrada : viejaTabla) {
            if (entrada != null && !entrada.borrado) {
                put(entrada.clave, entrada.valor);
            }
        }
    }
}
