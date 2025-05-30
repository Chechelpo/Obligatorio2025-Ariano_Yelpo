package Interfaces;

public interface MyHeapArray<T extends Comparable<T>> {
    public void agregar(T dato);
    public T obtenerYEliminar();
    public int obtenerTamano();
}
