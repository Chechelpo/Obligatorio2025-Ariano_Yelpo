package Utils.QuickSort;

import java.util.ArrayList;

public class QuickSort<T extends Comparable<T>> {

    public void quickSort(ArrayList<T> lista) {
        quickSort(lista, 0, lista.size() - 1);
    }

    private void quickSort(ArrayList<T> lista, int inicio, int fin) {
        if (inicio >= fin) return;

        int pivotIndex = particionar(lista, inicio, fin);
        quickSort(lista, inicio, pivotIndex - 1);
        quickSort(lista, pivotIndex + 1, fin);
    }

    private int particionar(ArrayList<T> lista, int inicio, int fin) {
        T pivot = lista.get(fin);
        int i = inicio;

        for (int j = inicio; j < fin; j++) {
            if (lista.get(j).compareTo(pivot) > 0) {
                T temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
                i++;
            }
        }

        T temp = lista.get(i);
        lista.set(i, lista.get(fin));
        lista.set(fin, temp);

        return i;
    }
}

