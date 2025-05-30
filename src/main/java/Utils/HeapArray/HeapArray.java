package Utils.HeapArray;

import Exceptions.HeapIsFullException;
import Interfaces.MyHeapArray;

public class HeapArray<T extends Comparable<T>> implements MyHeapArray<T> {
    private T[] heap;
    private int size;
    private final boolean esMaxHeap; // Indica si es un Max-Heap o un Min-Heap

    public int obtenerTamano() { //Getter
        return size;
    }

    @SuppressWarnings("unchecked")
    public HeapArray(int capacity, boolean esMaxHeap) {
        heap = (T[]) new Comparable[capacity];
        size = 0;
        this.esMaxHeap = esMaxHeap;
    }

    public void agregar(T value) throws HeapIsFullException {
        if (size >= heap.length) {
            throw new HeapIsFullException("El Heap esta lleno");
        }
        heap[size] = value; //agrego al final
        ajusteArriva(size); //Ajusto el elemento agregado hacia "arriva" (hacia la Izq)
        size++;
    }

    private void rotacion(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public T obtenerYEliminar() {
        if (size == 0) {
            return null;
        }
        T max = heap[0]; //Obtengo el valor máximo o mínimo según el heap
        heap[0] = heap[size - 1]; //pongo el valor del final arriva del todo
        size--;
        ajusteAbajo(0); // ajusto el valor que puse arriva del todo hacia abajo
        return max;
    }

    private void ajusteArriva(int index) {
        //rota con los padres hasta que llega a su lugar (cuando el padre es más chico si es MaxHeap, o más grande si es MinHeap)
        while (index > 0) {
            int parentIdx = (index - 1) / 2;
            if (esMaxHeap) {
                if (heap[index].compareTo(heap[parentIdx]) > 0) {
                    rotacion(index, parentIdx);
                    index = parentIdx;
                } else {
                    break;
                }
            } else {
                if (heap[index].compareTo(heap[parentIdx]) < 0) {
                    rotacion(index, parentIdx);
                    index = parentIdx;
                } else {
                    break;
                }
            }
        }
    }

    private void ajusteAbajo(int index) {
        while (index < size) {
            int leftChildIdx = 2 * index + 1;
            int rightChildIdx = 2 * index + 2;
            int elegido = index;

            if (esMaxHeap) {
                if (leftChildIdx < size && heap[leftChildIdx].compareTo(heap[elegido]) > 0) {
                    elegido = leftChildIdx;
                }
                if (rightChildIdx < size && heap[rightChildIdx].compareTo(heap[elegido]) > 0) {
                    elegido = rightChildIdx;
                }
            } else {
                if (leftChildIdx < size && heap[leftChildIdx].compareTo(heap[elegido]) < 0) {
                    elegido = leftChildIdx;
                }
                if (rightChildIdx < size && heap[rightChildIdx].compareTo(heap[elegido]) < 0) {
                    elegido = rightChildIdx;
                }
            }

            if (elegido != index) {
                rotacion(index, elegido);
                index = elegido;
            } else {
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Heap: ");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]).append(" ");
        }
        return sb.toString();
    }
}
